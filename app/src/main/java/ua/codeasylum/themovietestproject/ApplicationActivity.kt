package ua.codeasylum.themovietestproject

import android.os.Bundle
import ua.codeasylum.themovietestproject.base.BaseActivity

class ApplicationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)
    }
}
