package polak.shay.test.attenti

import android.app.Application
import polak.shay.test.attenti.Model.Matrix

class MyApp : Application()
{
    private var mMatrix : Matrix? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getData() : Matrix?
    {
        return mMatrix
    }

    fun setData(matrix : Matrix)
    {
        mMatrix = matrix
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }
}
