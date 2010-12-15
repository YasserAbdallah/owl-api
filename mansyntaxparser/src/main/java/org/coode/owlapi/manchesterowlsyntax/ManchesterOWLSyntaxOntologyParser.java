package org.coode.owlapi.manchesterowlsyntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.semanticweb.owlapi.expression.ParserException;
import org.semanticweb.owlapi.io.AbstractOWLParser;
import org.semanticweb.owlapi.io.OWLOntologyDocumentSource;
import org.semanticweb.owlapi.io.OWLParserException;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.UnloadableImportException;


/**
 * Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Date: 13-Aug-2007<br><br>
 */
public class ManchesterOWLSyntaxOntologyParser extends AbstractOWLParser {

    private static final String COMMENT_START_CHAR = "#";


    public OWLOntologyFormat parse(OWLOntologyDocumentSource documentSource, OWLOntology ontology) throws OWLParserException, IOException, UnloadableImportException {
        try {
            BufferedReader br = null;
            try {
                if (documentSource.isReaderAvailable()) {
                    br = new BufferedReader(documentSource.getReader());
                }
                else if (documentSource.isInputStreamAvailable()) {
                    br = new BufferedReader(new InputStreamReader(documentSource.getInputStream(), "UTF-8"));
                }
                else {
                    br = new BufferedReader(new InputStreamReader(getInputStream(documentSource.getDocumentIRI()), "UTF-8"));
                }
                StringBuilder sb = new StringBuilder();
                String line;
                int lineCount = 1;
                // Try to find the "magic number" (Prefix: or Ontology:)
                boolean foundMagicNumber = false;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                    if (!foundMagicNumber) {
                        String trimmedLine = line.trim();
                        if(trimmedLine.length() > 0) {
                            if(!trimmedLine.startsWith(COMMENT_START_CHAR)) {
                                // Non-empty line, that is not a comment.  The trimmed line MUST start with our magic
                                // number if we are going to parse the rest of it.
                                if (startsWithMagicNumber(line)) {
                                    foundMagicNumber = true;
                                    // We have set the found flag - we never end up here again
                                }
                                else {
                                    // Non-empty line that is NOT a comment.  We cannot possibly parse this.
                                    int startCol = line.indexOf(trimmedLine) + 1;
                                    StringBuilder msg = new StringBuilder();
                                    msg.append("Encountered '");
                                    msg.append(trimmedLine);
                                    msg.append("' at line ");
                                    msg.append(lineCount);
                                    msg.append(" column ");
                                    msg.append(startCol);
                                    msg.append(".  Expected either 'Ontology:' or 'Prefix:'");
                                    throw new ManchesterOWLSyntaxParserException(msg.toString(), lineCount, startCol);
                                }
                            }

                        }
                    }
                    lineCount++;
                }
                String s = sb.toString();
                ManchesterOWLSyntaxEditorParser parser = new ManchesterOWLSyntaxEditorParser(getOWLOntologyManager().getOWLDataFactory(), s);
                parser.parseOntology(getOWLOntologyManager(), ontology);
            }
            finally {
            	if(br!=null) {
            		br.close();
                }
            }
            return new ManchesterOWLSyntaxOntologyFormat();
        }
        catch (ParserException e) {
            throw new ManchesterOWLSyntaxParserException(e.getMessage(), e.getLineNumber(), e.getColumnNumber());
        }
    }

    private boolean startsWithMagicNumber(String line) {
        return line.indexOf(ManchesterOWLSyntax.PREFIX.toString()) != -1 || line.indexOf(ManchesterOWLSyntax.ONTOLOGY.toString()) != -1;
    }
}
