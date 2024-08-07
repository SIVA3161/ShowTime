package com.sivag.showtime.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sivag.network.client.ApiOperation
import com.sivag.showtime.data.model.remote.RemoteMovie
import com.sivag.showtime.data.service.IMovieService

class MoviesPagingSource(private val movieService: IMovieService) : PagingSource<Int, RemoteMovie.Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RemoteMovie.Result> {
        return try {
            val currentPage = params.key ?: 1
            val response = movieService.getPopularMovies(currentPage)

            if (response is ApiOperation.Success) {
                LoadResult.Page(
                    data = response.data,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (response.data.isEmpty()) null else currentPage + 1
                )
            } else {
                LoadResult.Error(Exception("Failed to load data"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RemoteMovie.Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
