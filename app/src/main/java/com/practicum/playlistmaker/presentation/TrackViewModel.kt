import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.practicum.playlistmaker.MyApplication
import com.practicum.playlistmaker.domain.api.TrackSearchInteractor

class TrackViewModel(
    private val trackId: String,
    private val tracksInteractor: TrackSearchInteractor
) : ViewModel() {

    init {
        Log.d("TEST", "init!: $trackId")
    }

    companion object {

        fun getViewModelFactory(trackId: String): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                // 1
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                    extras: CreationExtras,
                ): T {
                    // 2
                    val application = checkNotNull(extras[APPLICATION_KEY])

                    return TrackViewModel(
                        trackId,
                        // 3
                        (application as MyApplication).provideTrackSearchInteractor(),
                    ) as T
                }
            }
    }
}
