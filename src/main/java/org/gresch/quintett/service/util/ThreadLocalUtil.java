package org.gresch.quintett.service.util;

import java.lang.reflect.Field;

public class ThreadLocalUtil
{
  @SuppressWarnings("unchecked")
  public static void cleanupThreadLocals(Thread thread, final String classFilter, final ClassLoader classLoader)
  {
    if (classLoader != null)
    {
      System.out.println("@@ cleanupThreadLocals for classLoader=" + classLoader.getClass().getName() + "@" + Integer.toHexString(classLoader.hashCode()));
    }

    Thread[] threadList;
    if (thread != null)
    {
      threadList = new Thread[1];
      threadList[0] = thread;
    }
    else
    {
      // Every thread
      threadList = new Thread[Thread.activeCount()];
      Thread.enumerate(threadList);
    }

    for (int iThreadList = 0; iThreadList < threadList.length; iThreadList++)
    {
      Thread t = threadList[iThreadList];

      Field field;
      try
      {
        Class c;
        if (t instanceof java.lang.Thread)
        {
          c = t.getClass();
          while ((c != null) && (c != java.lang.Thread.class))
          {
            c = c.getSuperclass();
          }
          if (c != null)
          {
            field = c.getDeclaredField("threadLocals");
            field.setAccessible(true);

            Object threadLocals = field.get(t);
            if (threadLocals != null)
            {
              Field entries = threadLocals.getClass().getDeclaredField("table");
              entries.setAccessible(true);
              Object entryList[] = (Object[]) entries.get(threadLocals);
              for (int iEntry = 0; iEntry < entryList.length; iEntry++)
              {
                if (entryList[iEntry] != null)
                {
                  Field fValue = entryList[iEntry].getClass().getDeclaredField("value");
                  if (fValue != null)
                  {
                    fValue.setAccessible(true);
                    Object value = fValue.get(entryList[iEntry]);
                    if (value != null)
                    {
                      boolean flag = true;
                      System.out.println("found entry: value=" + value.getClass().getName() + "@" + Integer.toHexString(value.hashCode()));
                      if ((classFilter != null) && (value.getClass().getName().indexOf(classFilter) == -1))
                      {
                        flag = false;
                      }
                      if ((classLoader != null) && (value.getClass().getClassLoader() != classLoader))
                      {
                        flag = false;
                      }

                      if (flag)
                      {
                        System.out.println("@@ Entry for " + value.getClass().getName() + "@" + Integer.toHexString(value.hashCode()) + " cleared.");
                        entryList[iEntry] = null;
                      }
                    }
                  }
                }
              }
            }
            else
            {
              System.out.println("@@ no threadLocals for '" + t.getName() + "'");
            }
          }
        }
      }
      catch (IllegalAccessException ex)
      {
        //        Globals.reportException(ex);
      }
      catch (SecurityException ex)
      {
        //        Globals.reportException(ex);
      }
      catch (NoSuchFieldException ex)
      {
        //        Globals.reportException(ex);
        Field fields[] = t.getClass().getDeclaredFields();
        System.out.println("Fields available for " + t.getClass().getName() + ": ");
        for (int ii = 0; ii < fields.length; ii++)
        {
          System.out.println(fields[ii].getName());
        }
        System.out.println();
      }
    }
  }
}
