package org.sg.persistance;

import org.sg.domaine.Statement;

import java.util.List;

public interface StatementPersistence {
     List<Statement> getStatements(long AccountNumber);
     Statement saveStatement(long accountNumber, Statement statement);
}
