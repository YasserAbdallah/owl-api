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

package org.semanticweb.owlapi.io;

import org.semanticweb.owlapi.model.IRI;

/**
 * Author: Matthew Horridge<br>
 * The University of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Date: 21/12/2010
 * @since 3.2
 */
public class RDFResource extends RDFNode {

    private final IRI resource;

    private final boolean anonymous;

    /**
     * @param resource the resource
     * @param anonymous true if the resource is anonymous
     */
    public RDFResource(IRI resource, boolean anonymous) {
        this.resource = resource;
        this.anonymous = anonymous;
    }

    /**
     * Create an RDFResource that is NOT anonymous
     * @param resource The IRI of the resource
     */
    public RDFResource(IRI resource) {
        this(resource, false);
    }

    /**
     * Determines if this node is a literal node.
     * @return <code>true</code> if this node is a literal, otherwise <code>false</code>.
     */
    @Override
    public boolean isLiteral() {
        return false;
    }

    /**
     * @return the IRI
     */
    public IRI getResource() {
        return resource;
    }

    /**
     * @return true if resource is anonymous
     */
    public boolean isAnonymous() {
        return anonymous;
    }

    @Override
    public int hashCode() {
        return resource.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RDFResource)) {
            return false;
        }
        RDFResource other = (RDFResource) o;
        return resource.equals(other.resource) && anonymous == other.anonymous;
    }

    @Override
    public String toString() {
        if(!anonymous) {
            return resource.toQuotedString();
        }
        else {
            return "_:" + resource;
        }
    }
}
