/*
 * This file is part of the OWL API.
 *
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, The University of Manchester
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.semanticweb.owlapi.model;

import java.util.Set;


/**
 * Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Date: 12-Dec-2006<br><br>
 */
public abstract class OWLAxiomChange extends OWLOntologyChange {

    private final OWLAxiom axiom;

    /**
     * @param ont the ontology to which the change is to be applied
     * @param axiom the axiom to be changed
     */
    public OWLAxiomChange(OWLOntology ont, OWLAxiom axiom) {
        super(ont);
        this.axiom = axiom;
    }

    /**
     * Gets the signature of this ontology change.  That is, the set of entities appearing in objects in this change.
     * @return A set of entities that correspond to the
     *         signature of this object. The set is a copy, changes are not reflected back.
     */
    @Override
    public Set<OWLEntity> getSignature() {
        return axiom.getSignature();
    }

    @Override
	public boolean isAxiomChange() {
        return true;
    }


    /**
     * Determines if this change is an import change
     * @return <code>true</code> if this change is an import change, otherwise <code>false</code>.
     */
    @Override
	public boolean isImportChange() {
        return false;
    }





    /**
     * Gets the axiom that is involved in the change (the
     * axiom to either be added or removed)
     */
    @Override
	public OWLAxiom getAxiom() {
        return axiom;
    }


    /** A convenience method that obtains the entities which are referenced in
     * the axiom contained within this change.
     * 
     * @return A <code>Set</code> of entities which are referenced by the axiom
     *         contained within this change.
     * @deprecated use getSignature() instead */
    @Deprecated
    public Set<OWLEntity> getEntities() {
    	return axiom.getSignature();
    }
}
