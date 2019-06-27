package com.kanykeinu.cinematica.ui.list

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.kanykeinu.cinematica.R
import com.kanykeinu.cinematica.data.remote.responses.MovieInfoResponse
import com.kanykeinu.cinematica.databinding.MovieListFragmentBinding
import com.kanykeinu.cinematica.injection.Injector
import com.kanykeinu.cinematica.ui.MainActivity
import com.kanykeinu.cinematica.ui.base.BaseAdapter
import com.kanykeinu.cinematica.util.observe
import com.kanykeinu.cinematica.util.showSnackBar
import java.util.*

const val REQUEST_DATE_RANGE_LIMIT = 14

class MovieListFragment : Fragment(), BaseAdapter.ItemClickListener<MovieInfoResponse> {

    private lateinit var movieAdapter: MovieListAdapter
    private lateinit var dataBinding: MovieListFragmentBinding
    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val viewModelFactory = Injector.provideMovieListViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.viewModel = viewModel
        initViews()
        observeData()
        getMovies(null, null)
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.movie_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        openDatePicker()
        return super.onOptionsItemSelected(item)
    }

    private fun openDatePicker() {
        val date = Calendar.getInstance()
        val currentYear = date.get(Calendar.YEAR)
        val currentMonth = date.get(Calendar.MONTH)
        val currentDay = date.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { p0, p1, p2, p3 ->
                getMovies("$p1-$p2-$p3", "$currentYear-$currentMonth-$currentDay")
            },
            currentYear, currentMonth, currentDay
        )
        datePickerDialog.datePicker.maxDate = date.timeInMillis
        datePickerDialog.datePicker.minDate = date.timeInMillis - REQUEST_DATE_RANGE_LIMIT*86400000
        datePickerDialog.setTitle(getString(R.string.select_start_date))
        datePickerDialog.show()
    }

    override fun onClick(position: Int, item: MovieInfoResponse) {
        val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(item)
        findNavController().navigate(action)
    }

    private fun initViews() {
        movieAdapter = MovieListAdapter()
        movieAdapter.listener = this
        dataBinding.rvMovieList.setHasFixedSize(true)
        dataBinding.rvMovieList.adapter = movieAdapter
    }

    private fun observeData() {
        viewModel.movies.observe(this) {
            Log.d("movies ->", it.toString())
            movieAdapter.list = it
        }

        viewModel.error.observe(this) {
            dataBinding.root.showSnackBar(it)
            Log.d("Error->", it)
        }
    }

    private fun getMovies(startDate: String?, endDate: String?) {
        viewModel.loadMovieChanges(startDate = startDate, endDate = endDate)
    }

}
