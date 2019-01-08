package kotlin_bootcamp

import net.corda.core.contracts.ContractState
import net.corda.core.identity.AbstractParty
import net.corda.core.identity.Party

/* Our state, defining a shared fact on the ledger.
 */
class TokenState(val issuer:Party, val owner:Party, val amount: Int):ContractState{
    override val participants: List<AbstractParty> = listOf(issuer,owner)
}