package photomap.com.richard.photomap.core

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by rychardmatskevich on 12/21/17.
 */
@Parcelize
class PhotoItem(var title: String, var description: String, var imageName: String) : Parcelable
