package kotlin_bootcamp

import net.corda.core.contracts.*
import net.corda.core.transactions.LedgerTransaction

/* Our contract, governing how our state will evolve over time.
 */
class TokenContract : Contract {

    companion object {
        const val ID = "kotlin_bootcamp.TokenContract"
    }

    interface Commands : CommandData {
        class Issue : Commands
    }

    override fun verify(tx: LedgerTransaction) {

        requireThat {
            "Transaction should not have any inputs" using (tx.inputs.isEmpty())
            "Transaction should have only one output" using (tx.outputs.size == 1)

            var command = tx.commands.requireSingleCommand<Commands>()
            var outputState = tx.outputStates[0]

            "Output state must be a TokenState" using (outputState is TokenState)
            var tokenState = outputState as TokenState

            "Amount must be positive" using (tokenState.amount > 0)
            "Command must be an Issue command" using (command.value is TokenContract.Commands.Issue)
            "Issuer must sign the transaction" using (command.signers.contains(tokenState.issuer.owningKey))

        }

    }

}