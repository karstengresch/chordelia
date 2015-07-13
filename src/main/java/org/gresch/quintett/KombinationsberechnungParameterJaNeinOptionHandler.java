package org.gresch.quintett;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Localizable;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.BooleanOptionHandler;
import org.kohsuke.args4j.spi.Parameters;
import org.kohsuke.args4j.spi.Setter;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class KombinationsberechnungParameterJaNeinOptionHandler extends BooleanOptionHandler {

  private static final List<String> ACCEPTABLE_VALUES = Arrays.asList("j", "ja", "yes", "1", "n", "nein", "no", "0");
  private static Log log = LogFactory.getLog(KombinationsberechnungParameterJaNeinOptionHandler.class);
  private static CmdLineParser theParser = null;

  public KombinationsberechnungParameterJaNeinOptionHandler(CmdLineParser parser,
                                                            OptionDef option,
                                                            Setter<? super Boolean> setter) {

    super(parser, option, setter);
    theParser = parser;
  }

  @Override
  public int parseArguments(Parameters params) throws CmdLineException {
    log.info("Parsing: " + params);
    // Different compared to BooleanOptionHandler
    if (!option.isArgument()) {
      String valueStr = params.getParameter(0).toLowerCase();
      int index = ACCEPTABLE_VALUES.indexOf(valueStr);
      if (index == -1) {
        // TODO Check
        Localizable localizable = null;
        throw new CmdLineException(theParser, localizable, Messages.ILLEGAL_BOOLEAN.format(valueStr));
      }
      setter.addValue(index < ACCEPTABLE_VALUES.size() / 2);
      return 1;
    } else {
      setter.addValue(true);
      return 0;
    }
  }

  @Override
  public String getDefaultMetaVariable() {
    return null;
  }

  enum Messages {
    ILLEGAL_OPERAND,
    ILLEGAL_BOOLEAN;

    private static ResourceBundle rb;

    public String format(Object... args) {
      synchronized (Messages.class) {
        if (rb == null)
          rb = ResourceBundle.getBundle(Messages.class.getName(), Locale.getDefault(), this.getClass().getClassLoader());
        return MessageFormat.format(rb.getString(name()), args);
      }
    }
  }

}
