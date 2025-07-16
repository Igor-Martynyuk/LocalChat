package com.dev.martyniuk.local.chat.ui.view.view

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import androidx.core.widget.doAfterTextChanged
import com.dev.martyniuk.local.chat.core.common.extensions.isNull
import com.dev.martyniuk.local.chat.core.common.extensions.toInt
import com.google.android.material.textfield.TextInputLayout

//A TextInputLayout, ignoring errors on empty or non-focused field
//To show error input should not be empty and EditText should be focused at least once
class SmartErrorTextInputLayout @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {
    private var pendingError: CharSequence? = null
    private val hasText: Boolean get() = editText?.text?.isEmpty() == false
    private var hadFocus: Boolean = false
        set(value) {
            field = value
            error = pendingError
        }

    init {
        addOnEditTextAttachedListener {
            editText?.setOnFocusChangeListener { _, isFocused -> if (isFocused) hadFocus = true }
            editText?.doAfterTextChanged {
                if (it.toString().isEmpty()) super.setError(null)
                else if (error != pendingError) error = pendingError
            }
        }
    }

    override fun onSaveInstanceState(): Parcelable? = super.onSaveInstanceState()?.let {
        State(it).also { state -> state.hadFocus = this.hadFocus }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        (state as? State)?.let {
            super.onRestoreInstanceState(state.superState)
            this.hadFocus = state.hadFocus
        }
        super.onRestoreInstanceState(state)
    }

    private fun trySetError(errorText: String?) {
        if (errorText.isNull()) super.setError(null)
        else if (hadFocus && hasText) super.setError(errorText)

        pendingError = errorText
        isErrorEnabled = error.isNull().not()
    }

    fun setError(errorResId: Int?) = trySetError(
        if (errorResId.isNull()) null else context.getString(errorResId!!)
    )

    override fun setError(errorText: CharSequence?) = trySetError(
        if (errorText.isNull()) null else errorText.toString()
    )

    private class State : BaseSavedState {
        var hadFocus: Boolean = false

        constructor(superState: Parcelable) : super(superState)
        constructor(parcel: Parcel) : super(parcel) {
            hadFocus = parcel.readInt() == 1
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(hadFocus.toInt())
        }

        companion object CREATOR : Parcelable.Creator<State> {
            override fun createFromParcel(parcel: Parcel): State {
                return State(parcel)
            }

            override fun newArray(size: Int): Array<State?> {
                return arrayOfNulls(size)
            }
        }
    }
}