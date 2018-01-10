package photomap.com.richard.photomap.presentation.photo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_photo.*
import photomap.com.richard.photomap.R
import photomap.com.richard.photomap.core.PhotoItem

class PhotoActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val photoItem = intent.getParcelableExtra<PhotoItem>("photoItem")

        titleTextView.text = photoItem.title
        dateTextView.text = photoItem.description
        photo_view.setImageResource(R.drawable.ic_1920_1200_201008100955062320111)
        photo_view.setOnClickListener(this)

        backButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id) {
                backButton.id -> finish()
                photo_view.id -> {
                    topView.visibility = if (topView.isShown) View.GONE else View.VISIBLE
                    bottomView.visibility = if (bottomView.isShown) View.GONE else View.VISIBLE
                }
            }
        }
    }

}
