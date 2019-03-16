package polak.shay.test.attenti.Model

import java.util.*


class Matrix(col: Int, row: Int, initialize : Boolean) {

    private var mData: Array<Array<Int>>? = null
    private var mCol = 0
    private var mRow = 0

    init {
        if (row > 0 && col > 0) {
            if(initialize) {
                val randomer = Random()
                mData = Array(col, {i -> Array(row, {j -> randomer.nextInt(2)})})
            }
            mCol = col
            mRow = row
        }
    }

    constructor(matrix: Matrix) : this(matrix.getCol(), matrix.getRow(), false)
    {
        mData = Array(matrix.mCol, {i -> Array(matrix.mRow, {j -> matrix.get(i, j)})})
    }

    fun getCol() = mCol

    fun getRow() = mRow

    fun get(col: Int, row: Int) : Int {
        if(col < 0 || col >= mCol || row < 0 || row >= mRow)
        {
            return -1
        }
        else {
            return mData!!.get(col).get(row)
        }
    }

    fun set(col: Int, row: Int, value: Int) = mData?.get(col)?.set(row, value)
}