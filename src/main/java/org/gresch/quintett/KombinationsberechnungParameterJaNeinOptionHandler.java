package org.gresch.quintett;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.BooleanOptionHandler;
import org.kohsuke.args4j.spi.Parameters;
import org.kohsuke.args4j.spi.Setters;

public class KombinationsberechnungParameterJaNeinOptionHandler extends BooleanOptionHandler
{

  private static Log log = LogFactory.getLog(KombinationsberechnungParameterJaNeinOptionHandler.class);

  enum Messages
  {
    ILLEGAL_OPERAND,
    ILLEGAL_BOOLEAN;

    private static ResourceBundle rb;

    public String format(Object... args)
    {
      synchronized (Messages.class)
      {
        if (rb == null)
          rb = ResourceBundle.getBundle(Messages.class.getName(), Locale.getDefault(), this.getClass().getClassLoader());
        return MessageFormat.format(rb.getString(name()), args);
      }
    }
  }

  private static final List<String> ACCEPTABLE_VALUES = Arrays.asList(new String[] { "j", "ja", "yes", "1", "n", "nein", "no", "0" });

  public KombinationsberechnungParameterJaNeinOptionHandler(CommandLineParser parser,
                                                            Option option,
                                                            Setters<? super Boolean> setter)
  {
    super(parser, option, setter);
  }

  @Override
  public int parseArguments(Parameters params) throws CmdLineException
  {
    log.info("Parsing: " + params);
    // Different compared to BooleanOptionHandler
    if (!option.isArgument())
    {
      String valueStr = params.getParameter(0).toLowerCase();
      int index = ACCEPTABLE_VALUES.indexOf(valueStr);
      if (index == -1)
      {
        throw new CmdLineException(Messages.ILLEGAL_BOOLEAN.format(valueStr));
      }
      setter.addValue(index < ACCEPTABLE_VALUES.size() / 2);
      return 1;
    }
    else
    {
      setter.addValue(true);
      return 0;
    }
  }

  @Override
  public String getDefaultMetaVariable()
  {
    return null;
  }

}
