package ru.yandex.money.qa.tests.utils

import io.qameta.allure.Allure
import io.qameta.allure.model.Status
import io.qameta.allure.model.StepResult
import io.qameta.allure.util.ResultsUtils
import java.util.*

class AllureHelper {
    companion object {
        fun makeStep(name: String, runnable: () -> Unit) {
            val uuid = UUID.randomUUID().toString()
            val result = StepResult().setName(name)
            Allure.getLifecycle().startStep(uuid, result)
            try {
                runnable()
                Allure.getLifecycle().updateStep(uuid) { s -> s.status = Status.PASSED }
            } catch (e: Throwable) {
                Allure.getLifecycle().updateStep(uuid) { s ->
                    s.setStatus(ResultsUtils.getStatus(e).orElse(Status.BROKEN)).statusDetails =
                        ResultsUtils.getStatusDetails(e).orElse(null)
                }
                throw e
            } finally {
                Allure.getLifecycle().stopStep(uuid)
            }
        }
    }
}