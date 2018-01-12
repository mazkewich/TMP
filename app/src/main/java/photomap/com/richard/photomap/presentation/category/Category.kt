package photomap.com.richard.photomap.presentation.category

import android.graphics.Color

/**
 * @author richard on 1/12/18.
 */

data class Category(
        val name: String,
        val color: Int,
        var selected: Boolean
)