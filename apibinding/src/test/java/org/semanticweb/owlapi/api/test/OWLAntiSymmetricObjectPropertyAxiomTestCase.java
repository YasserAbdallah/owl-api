package org.semanticweb.owlapi.api.test;

import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLPropertyAxiom;


/**
 * Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Bio-Health Informatics Group
 * Date: 25-Oct-2006
 */
public class OWLAntiSymmetricObjectPropertyAxiomTestCase extends AbstractOWLObjectPropertyCharacteristicAxiomTestCase {

    @Override
	protected OWLPropertyAxiom createOWLPropertyAxiom(OWLObjectProperty property) throws OWLException {
        return getFactory().getOWLAsymmetricObjectPropertyAxiom(property);
    }
}
