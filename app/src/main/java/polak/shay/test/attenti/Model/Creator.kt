package polak.shay.test.attenti.Model

import android.os.Handler
import android.os.Looper

class Creator
{
    private val mMainHandler : Handler = Handler(Looper.getMainLooper())

    constructor(col : Int, row : Int, listener : CreatorListener? ){
         Thread().run{
            val result = Matrix(col, row, true)
             mMainHandler.post(Runnable {
                 listener?.result(result)
             })
        }
    }

    constructor(matrix: Matrix, listener : CreatorListener?)
    {
        Thread().run{
            val result = Matrix(matrix)
            mMainHandler.post(Runnable {
                listener?.result(result)
            })
        }
    }

    public interface CreatorListener
    {
        fun result(m : Matrix)
    }
}