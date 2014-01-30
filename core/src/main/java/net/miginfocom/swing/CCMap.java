package net.miginfocom.swing;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.ComponentWrapper;

import java.util.*;

/**
This class represents the first and hopefully last step in a fork of MigLayout.
I have registered on the forums, and can login, but still can't post about it.  Perhaps it is because I just registered.

Anyhow, the issue is a ConcurrentModificationException, which only happens about 1/3 of the time.
Just running SwingSyncBrowserRunnerMain will trigger the stack trace if you are (un)lucky.
The full stack trace follows below.

This class was split out of MigLayout in order to help understand and resolve the problem.

As far as I can tell:
 - only the EDT is accessing the map
 - it is accessing it properly

So, it appears to be:
 - a bug in HashMap
 - a bug in the JVM

Here's the version info:
 C:\Program Files\Java\jdk1.8.0\bin>java -version
 java version "1.8.0-ea"
 Java(TM) SE Runtime Environment (build 1.8.0-ea-b124)
 Java HotSpot(TM) 64-Bit Server VM (build 25.0-b66, mixed mode)

 C:\Program Files\Java\jdk1.8.0\bin>ver

 Microsoft Windows [Version 6.1.7601]

Using ConcurrentHashMap instead of HashMap makes the problem go away, or at least rare enough that it looks to be
gone.  That is the change I will make as soon as I commit this bug-demonstrating fork.

<pre>
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.setComponentConstraintsImpl(MigLayout.java:307)put
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.setComponentConstraintsImpl(MigLayout.java:307)put
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.setComponentConstraintsImpl(MigLayout.java:307)put
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.setComponentConstraintsImpl(MigLayout.java:307)put
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.setComponentConstraintsImpl(MigLayout.java:307)put
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.setComponentConstraintsImpl(MigLayout.java:307)put
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.setComponentConstraintsImpl(MigLayout.java:307)put
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.cleanConstraintMaps(MigLayout.java:489)key set start
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.cleanConstraintMaps(MigLayout.java:496)removeAll
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.checkCache(MigLayout.java:444)key set start
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.preferredLayoutSize(MigLayout.java:670)key set start
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.cleanConstraintMaps(MigLayout.java:489)key set start
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.cleanConstraintMaps(MigLayout.java:496)removeAll
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.checkCache(MigLayout.java:476)asMap
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.cleanConstraintMaps(MigLayout.java:489)key set start
 Thread[AWT-EventQueue-0,6,main] net.miginfocom.swing.MigLayout.cleanConstraintMaps(MigLayout.java:496)removeAll
 Exception in thread "AWT-EventQueue-0" java.lang.reflect.UndeclaredThrowableException
 at com.sun.proxy.$Proxy1.layoutContainer(Unknown Source)
 at java.awt.Container.layout(Container.java:1508)
 at java.awt.Container.doLayout(Container.java:1497)
 at java.awt.Container.validateTree(Container.java:1693)
 at java.awt.Container.validate(Container.java:1628)
 at javax.swing.JLayeredPane.addImpl(JLayeredPane.java:232)
 at java.awt.Container.add(Container.java:971)
 at javax.swing.JRootPane.setContentPane(JRootPane.java:626)
 at javax.swing.JFrame.setContentPane(JFrame.java:695)
 at net.baremodels.device.swing.SwingSyncDevice.redisplay(SwingSyncDevice.java:105)
 at net.baremodels.device.swing.SwingSyncDevice.lambda$display$2(SwingSyncDevice.java:74)
 at net.baremodels.device.swing.SwingSyncDevice$$Lambda$8/1028214719.run(Unknown Source)
 at java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:311)
 at java.awt.EventQueue.dispatchEventImpl(EventQueue.java:744)
 at java.awt.EventQueue.access$400(EventQueue.java:97)
 at java.awt.EventQueue$3.run(EventQueue.java:697)
 at java.awt.EventQueue$3.run(EventQueue.java:691)
 at java.security.AccessController.doPrivileged(Native Method)
 at java.security.ProtectionDomain$1.doIntersectionPrivilege(ProtectionDomain.java:75)
 at java.awt.EventQueue.dispatchEvent(EventQueue.java:714)
 at java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:201)
 at java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:116)
 at java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:105)
 at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
 at java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:93)
 at java.awt.EventDispatchThread.run(EventDispatchThread.java:82)
 Caused by: java.lang.reflect.InvocationTargetException
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:483)
 at net.baremodels.device.swing.EDTWrapper$1.invoke(EDTWrapper.java:23)
 ... 26 more
 Caused by: java.util.ConcurrentModificationException
 at java.util.HashMap$HashIterator.nextNode(HashMap.java:1429)
 at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
 at java.util.Collections$UnmodifiableCollection$1.next(Collections.java:1102)
 at net.miginfocom.swing.MigLayout.checkCache(MigLayout.java:444)
 at net.miginfocom.swing.MigLayout.layoutContainer(MigLayout.java:529)
 ... 31 more
 </pre>
 */
final class CCMap {

    int access;
    private final Map<ComponentWrapper, CC> map = new HashMap<ComponentWrapper, CC>(8);

    void clear() {
        print("clear");
        map.clear();
    }

    void remove(ComponentWrapper key) {
        print("remove");
        map.remove(key);
    }

    void removeAll(List<ComponentWrapper> toRemove) {
        print("removeAll");
        map.keySet().removeAll(toRemove);
    }

    void put(ComponentWrapper key, CC value) {
        print("put");
        map.put(key, value);
    }

    Set<ComponentWrapper> keySet() {
        print("key set");
        Set<ComponentWrapper> copy = new HashSet<>();
        copy.addAll(Collections.unmodifiableSet(map.keySet()));
        return Collections.unmodifiableSet(copy);
    }

    Map<ComponentWrapper, CC> asMap() {
        print("asMap");
        Map<ComponentWrapper, CC> copy = new HashMap<>();
        copy.putAll(Collections.unmodifiableMap(map));
        return Collections.unmodifiableMap(copy);
    }

    private void print(String message) {
        //System.err.println(Thread.currentThread() + " " + access + " " + Thread.currentThread().getStackTrace()[3] + message);
        access++;
    }

}
