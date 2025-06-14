package io.github.ringtonesmartkit.api

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import io.github.ringtonesmartkit.contract.ActivityTracker
import io.github.ringtonesmartkit.domain.model.ContactIdentifier
import io.github.ringtonesmartkit.domain.model.RingtoneData
import io.github.ringtonesmartkit.domain.model.RingtoneSource
import io.github.ringtonesmartkit.domain.model.RingtoneTarget
import io.github.ringtonesmartkit.domain.model.RingtoneType
import io.github.ringtonesmartkit.viewmodules.ContactViewModel

object RingtoneHelper {

    private val viewModel: ContactViewModel
        get() = ViewModelProvider(
            ActivityTracker.currentActivity as ComponentActivity
        )[ContactViewModel::class.java]


    /**
     * تحميل نغمة من مصدر بدون تطبيقها.
     */
    fun loadRingtone(
        source: RingtoneSource,
        onLoaded: (RingtoneData?) -> Unit,
        onError: (Throwable) -> Unit = {},
    ) {
        viewModel.loadRingtone(source, onLoaded, onError)
    }


    /**
     * تحميل وتطبيق نغمة على نوع نظام معين (CALL / NOTIFICATION / ALARM)
     */
    fun setSystemRingtone(
        source: RingtoneSource,
        type: RingtoneType = RingtoneType.CALL,
        onSuccess: () -> Unit = {},
        onError: (Throwable) -> Unit = {},
    ) {
        viewModel.setSystemRingtone(
            source = source,
            type = type,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    fun setContactRingtone(
        source: RingtoneSource,
        contact: ContactIdentifier,
        onSuccess: () -> Unit = {},
        onError: (Throwable) -> Unit = {},
    ) {
        viewModel.applyToTarget(
            source = source,
            target = RingtoneTarget.ContactTarget.Provided(contact),
            onSuccess = onSuccess,
            onError = onError
        )
    }


    /**
     * تحميل وتطبيق نغمة على هدف معين (بما فيه Custom Target)
     */
    fun applyToTarget(
        source: RingtoneSource,
        target: RingtoneTarget,
        onSuccess: () -> Unit = {},
        onError: (Throwable) -> Unit = {},
    ) {
        viewModel.applyToTarget(source, target, onSuccess, onError)
    }
}
