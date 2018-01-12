package photomap.com.richard.photomap.services

import android.graphics.Color
import photomap.com.richard.photomap.presentation.category.Category

/**
 * @author richard on 1/12/18.
 */

object DataService {

    val categories: List<Category> = listOf<Category>(
            Category("DEFAULT", Color.BLUE, true),
            Category("NATURE", Color.GREEN, true),
            Category("FRIEND", Color.YELLOW, true))

}