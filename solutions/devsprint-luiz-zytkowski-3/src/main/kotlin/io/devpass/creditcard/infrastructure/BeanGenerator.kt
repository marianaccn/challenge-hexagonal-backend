package io.devpass.creditcard.infrastructure

import io.devpass.creditcard.data.*
import io.devpass.creditcard.data.entities.CreditCardEntity
import io.devpass.creditcard.data.AccountManagementGateway
import io.devpass.creditcard.data.AntiFraudGateway
import io.devpass.creditcard.data.CreditCardDAO
import io.devpass.creditcard.data.CreditCardInvoiceDAO
import io.devpass.creditcard.data.CreditCardOperationDAO
import io.devpass.creditcard.data.repositories.CreditCardInvoiceRepository
import io.devpass.creditcard.data.repositories.CreditCardOperationRepository
import io.devpass.creditcard.data.repositories.CreditCardRepository
import io.devpass.creditcard.dataaccess.IAccountManagementGateway
import io.devpass.creditcard.dataaccess.IAntiFraudGateway
import io.devpass.creditcard.dataaccess.ICreditCardDAO
import io.devpass.creditcard.dataaccess.ICreditCardInvoiceDAO
import io.devpass.creditcard.dataaccess.ICreditCardOperationDAO
import io.devpass.creditcard.domain.CreditCardInvoiceService
import io.devpass.creditcard.domain.CreditCardOperationService
import io.devpass.creditcard.domain.CreditCardService
import io.devpass.creditcard.domainaccess.ICreditCardInvoiceServiceAdapter
import io.devpass.creditcard.domainaccess.ICreditCardOperationServiceAdapter
import io.devpass.creditcard.domainaccess.ICreditCardServiceAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanGenerator {

    @Bean
    fun creditCardDAO(
        creditCardRepository: CreditCardRepository,
    ): ICreditCardDAO {
        return CreditCardDAO(creditCardRepository)
    }

    @Bean
    fun creditCardServiceAdapter(
        creditCardRepository: ICreditCardDAO,
        accountManagementGateway: IAccountManagementGateway,
        antiFraudGateway: IAntiFraudGateway,
    ): ICreditCardServiceAdapter {
        return CreditCardService(creditCardRepository, accountManagementGateway, antiFraudGateway)
    }

    @Bean
    fun creditCardOperationDAO(creditCardOperationRepository: CreditCardOperationRepository): ICreditCardOperationDAO {
        return CreditCardOperationDAO(creditCardOperationRepository)
    }

    @Bean
    fun creditCardOperationServiceAdapter(
        creditCardOperationDAO: ICreditCardOperationDAO,
        creditCardDAO: ICreditCardDAO,
    ): ICreditCardOperationServiceAdapter {
        return CreditCardOperationService(creditCardOperationDAO, creditCardDAO)
    }

    @Bean
    fun creditCardInvoiceDAO(
        creditCardInvoiceRepository: CreditCardInvoiceRepository,
    ): ICreditCardInvoiceDAO {
        return CreditCardInvoiceDAO(creditCardInvoiceRepository)
    }

    @Bean
    fun creditCardInvoiceServiceAdapter(
        creditCardDAO: ICreditCardDAO,
        creditCardInvoiceDAO: ICreditCardInvoiceDAO,
        creditCardOperationDAO: ICreditCardOperationDAO,
    ): ICreditCardInvoiceServiceAdapter {
        return CreditCardInvoiceService(
            creditCardDAO,
            creditCardInvoiceDAO,
            creditCardOperationDAO,
        )
    }

    @Bean
    fun antiFraudGateway(): IAntiFraudGateway {
        return AntiFraudGateway("http://localhost:7444")
    }

    @Bean
    fun accountManagementGateway(): IAccountManagementGateway {
        return AccountManagementGateway("http://localhost:7445")
    }
}
