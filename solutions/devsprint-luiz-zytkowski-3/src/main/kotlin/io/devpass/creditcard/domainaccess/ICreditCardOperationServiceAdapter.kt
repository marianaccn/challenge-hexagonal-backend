package io.devpass.creditcard.domainaccess

import io.devpass.creditcard.domain.objects.CreditCardCharge
import io.devpass.creditcard.domain.objects.CreditCardOperation

interface ICreditCardOperationServiceAdapter {
    fun getById(creditCardOperationId: String): CreditCardOperation?
    fun createCreditCardOperation(creditCardCharge: CreditCardCharge): List<CreditCardOperation>
    fun chargeCreditCard(creditCardCharge: CreditCardCharge): List<CreditCardOperation>

}
