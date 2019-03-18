package polak.shay.test.attenti.Model

import android.os.Handler
import android.os.Looper
import java.util.*

class Finder(matrix: Matrix, listener: FinderListener?) : Creator.CreatorListener {

    private val mMainHandler: Handler = Handler(Looper.getMainLooper())
    private var mListener: FinderListener? = null
    private lateinit var mMatrix: Matrix
    private var mIslandNumber = 0
    private var mStack = Stack<String>()

    init {
        mListener = listener
        Creator(matrix, this)
    }

    override fun result(m: Matrix) {
        Thread().run {
            mMatrix = m
            search()
            mMainHandler.post(Runnable {
                mListener?.returnIslandNumber(mMatrix, mIslandNumber)
                mListener = null
            })
        }
    }

    private fun search() {
        for (col : Int in 0..mMatrix.getCol()) {
            for (row : Int in 0..mMatrix.getRow()) {
                if (mMatrix.get(col, row) == 1) {
                    paint(col , row, mIslandNumber + 2)
                    while (mStack.size > 0) {
                        val index = mStack.pop().split(",").toTypedArray()
                        paint(index[0].toInt() , index[1].toInt(), index[2].toInt())
                    }
                    mIslandNumber++
                }
            }
        }
    }

    private fun paint(col: Int, row: Int, color: Int){
        for (i: Int in -1..1) {
            for (j: Int in -1..1) {
                if (mMatrix.get(col + i, row + j) == 1) {
                    mMatrix.set(col + i, row + j, color)
                    mMatrix.set(col, row, color)
                    mStack.push("${col+i},${row+j},$color")
                }
            }
        }
    }

    interface FinderListener {
        fun returnIslandNumber(matrix: Matrix, islandNumber: Int)
    }
}