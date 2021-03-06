/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.content.authority;

/**
 * This is a *very* stupid test fixture for authority control, and also
 * serves as a trivial example of an authority plugin implementation.
 */
public class SampleAuthority implements ChoiceAuthority
{
    private static String values[] = {
        "sun",
        "mon",
        "tue",
        "wed",
        "thu",
        "fri",
        "sat"
    };

    private static String labels[] = {
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"
    };

    public Choices getMatches(String field, String query, int collection, int start, int limit, String locale)
    {
        int dflt = -1;
        Choice v[] = new Choice[values.length];
        for (int i = 0; i < values.length; ++i)
        {
            v[i] = new Choice(String.valueOf(i), values[i], labels[i]);
            if (values[i].equalsIgnoreCase(query))
            {
                dflt = i;
            }
        }
        return new Choices(v, 0, v.length, Choices.CF_AMBIGUOUS, false, dflt);
    }

    public Choices getBestMatch(String field, String text, int collection, String locale)
    {
        for (int i = 0; i < values.length; ++i)
        {
            if (text.equalsIgnoreCase(values[i]))
            {
                Choice v[] = new Choice[1];
                v[0] = new Choice(String.valueOf(i), values[i], labels[i]);
                return new Choices(v, 0, v.length, Choices.CF_UNCERTAIN, false, 0);
            }
        }
        return new Choices(Choices.CF_NOTFOUND);
    }

    public String getLabel(String field, String key, String locale)
    {
        return labels[Integer.parseInt(key)];
    }
}
