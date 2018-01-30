package photomap.com.richard.photomap.presentation.category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author richard on 1/12/18.
 */
@Parcelize
data class Category(
        val name: String,
        val color: Int,
        var selected: Boolean
) : Parcelable