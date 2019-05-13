package com.odinrossy.banksystem.services.account.abstraction

import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface CloseBankDayService extends LoggerService {

    def closeBankDay(args)

}
