package org.symbench.creointerferenceserver.http;

import org.symbench.creointerferenceserver.creo.InterferenceAnalyzer;

import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

public class InterferenceHandler extends JSONCommandHandler {
    public static final String COMMAND = "interference";

    public static final String GLOBAL_INTERFERENCE = "global_interference";

    public static final String ASSEMBLY_PATH = "assembly_path";

    private static final Logger logger = Logger.getLogger(InterferenceHandler.class.getName());

    private InterferenceAnalyzer interferenceAnalyzer;

    public InterferenceHandler(InterferenceAnalyzer analyzer) {
        this.interferenceAnalyzer = analyzer;
    }


    public Hashtable<String, Object> handleFunction(String function, Hashtable<String, Object> input) throws Exception {
        Hashtable<String, Object> output;
        logger.info("Function " + function + " called with data "  + input);
        if(function.equals(GLOBAL_INTERFERENCE)) {
            return computeGlobalInterference(input);
        } else {
            throw new UnsupportedOperationException("Function " + function + " is not supported");
        }
    }

    private Hashtable<String, Object> computeGlobalInterference(Hashtable<String, Object> input) throws Exception {
        if(input == null) {
            return this.interferenceAnalyzer.getGlobalInterferences();
        }
        String  assemblyPath = (String) input.get(ASSEMBLY_PATH);
        if (assemblyPath == null) {
            return this.interferenceAnalyzer.getGlobalInterferences();
        } else {
            return this.interferenceAnalyzer.getGlobalInterferences(assemblyPath);
        }
    }
}
