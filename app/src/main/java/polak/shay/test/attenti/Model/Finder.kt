package polak.shay.test.attenti.Model

import android.os.Handler
import android.os.Looper

class Finder(matrix: Matrix, listener: FinderListener?) : Creator.CreatorListener {

    private val mMainHandler: Handler = Handler(Looper.getMainLooper())
    private var mListener: FinderListener? = null
    private lateinit var mMatrix: Matrix
    private var mIslandNumber = 0

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
        for (i: Int in 0..mMatrix.getCol()) {
            for (j: Int in 0..mMatrix.getCol()) {
                if (mMatrix.get(i, j) == 1) {
                    if (paint(i, j, mIslandNumber + 2, false)) {
                        mIslandNumber++
                    }
                }
            }
        }
    }

    private fun paint(col: Int, row: Int, color: Int, finder: Boolean): Boolean {
        var afterFind = finder
        for (i: Int in -1..1) {
            for (j: Int in -1..1) {
                if (i == 0 && j == 0) {
                    continue
                } else if (mMatrix.get(col + i, row + j) == 1) {
                    mMatrix.set(col + i, row + j, color)
                    mMatrix.set(col, row, color)
                    afterFind = true
                    paint(col + i, row + j, color, afterFind)
                }
            }
        }
        return afterFind
    }

    interface FinderListener {
        fun returnIslandNumber(matrix: Matrix, islandNumber: Int)
    }
}