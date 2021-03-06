package polak.shay.test.attenti.View.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_display.*
import polak.shay.test.attenti.MyApp
import polak.shay.test.attenti.R
import android.view.View
import android.widget.TextView
import android.widget.Toast
import polak.shay.test.attenti.Model.Finder
import polak.shay.test.attenti.Model.Matrix
import polak.shay.test.attenti.View.Adapters.MatrixDisplayAdapter
import android.support.v7.widget.RecyclerView




class DisplayActivity : AppCompatActivity(), Finder.FinderListener {

    private var mAdapter: MatrixDisplayAdapter? = null
    private var mDisplayPaint = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        setupView()
    }


    private fun setupView() {
        val matrix = MyApp.instance.getData()
        val recycledViewPool = RecyclerView.RecycledViewPool()
        recycledViewPool.setMaxRecycledViews(0, 15)
        displayMatrix.setRecycledViewPool(recycledViewPool)
        displayMatrix.setLayoutManager(GridLayoutManager(this, matrix!!.getRow(), GridLayoutManager.HORIZONTAL, false))
        mAdapter = MatrixDisplayAdapter(this, matrix)
        displayMatrix.adapter = mAdapter
    }

    fun paintMatix(v: View) {
        if(!mDisplayPaint)
        {
            (v as TextView).setText(R.string.reset)
            mAdapter?.removeClickListener()
            Finder(MyApp.instance.getData()!!, this)
            mDisplayPaint = true
        }
        else
        {
            (v as TextView).setText(R.string.solve)
            mAdapter?.setClickListener()
            mAdapter?.updateDate(MyApp.instance.getData()!!)
            mDisplayPaint = false
        }
    }

    override fun returnIslandNumber(matrix: Matrix, islandNumber: Int) {
        mAdapter?.updateDate(matrix)
        Toast.makeText(this, "the number is $islandNumber", Toast.LENGTH_LONG).show()
    }
}