# Cinematime

Implementing clean architecture in android and using some of the new ConcatAdapter:

<p align="center">
  <img src="https://github.com/santimattius/android-cinematime/blob/master/screenshot/android_cinematime.png?raw=true" alt="App Capture"/>
</p>

## ConcatAdapter
[Documentation](https://developer.android.com/reference/androidx/recyclerview/widget/ConcatAdapter)

``` Kotlin

    private val moviesAdapter: MovieAdapter by lazy {
        MovieAdapter(ItemViewHolder.ItemClick {
            viewModel.onContentClicked(it)
        })
    }

    private val tvAdapter: TvAdapter by lazy {
        TvAdapter(ItemViewHolder.ItemClick {
            viewModel.onContentClicked(it)
        })
    }

    private val principalAdapter: ConcatAdapter = ConcatAdapter()
    ...
    principalAdapter.addAdapter(moviesAdapter)
    principalAdapter.addAdapter(tvAdapter)
    
```
## Dependencies

Below you will find the libraries used to build the template and according to my criteria the most used in android development so far.

- **Koin** - dependencie provider: 
  - https://insert-koin.io/
- **Retrofit** - networking: 
  - https://square.github.io/retrofit/
- **Moshi** - json parser:
  - https://github.com/square/moshi
- **Glide** with image loader:
  - https://github.com/bumptech/glide
- **Kotlin coroutines**
  - https://kotlinlang.org/docs/reference/coroutines-overview.html
- **Mockk**, testing library
  - https://mockk.io/  
