package com.github.pampas.common.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.concurrent.*;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-05
 */
public class InetTools implements Closeable {


    // TODO: maybe shutdown the thread pool if it isn't being used?
    private final ExecutorService executorService;
    private static final InetTools instance = new InetTools();

    private static final Logger log = LoggerFactory.getLogger(InetTools.class);

    public InetTools() {
        this.executorService = Executors
                .newSingleThreadExecutor(new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("pampas-inettools");
                        thread.setDaemon(true);
                        return thread;
                    }
                });
    }

    @Override
    public void close() {
        executorService.shutdown();
    }

    public HostInfo findFirstNonLoopbackHostInfo() {
        InetAddress address = findFirstNonLoopbackAddress();
        if (address != null) {
            return convertAddress(address);
        }
        HostInfo hostInfo = new HostInfo();
        hostInfo.setHostname("localhost");
        hostInfo.setIpAddress("127.0.0.1");
        return hostInfo;
    }

    public InetAddress findFirstNonLoopbackAddress() {
        InetAddress result = null;
        try {
            int lowest = Integer.MAX_VALUE;
            for (Enumeration<NetworkInterface> nics = NetworkInterface
                    .getNetworkInterfaces(); nics.hasMoreElements(); ) {
                NetworkInterface ifc = nics.nextElement();
                if (ifc.isUp()) {
                    log.trace("Testing interface: " + ifc.getDisplayName());
                    if (ifc.getIndex() < lowest || result == null) {
                        lowest = ifc.getIndex();
                    } else if (result != null) {
                        continue;
                    }

                    // @formatter:off
                    if (!ignoreInterface(ifc.getDisplayName())) {
                        for (Enumeration<InetAddress> addrs = ifc
                                .getInetAddresses(); addrs.hasMoreElements(); ) {
                            InetAddress address = addrs.nextElement();
                            if (address instanceof Inet4Address
                                    && !address.isLoopbackAddress()
                                    && !ignoreAddress(address)) {
                                log.trace("Found non-loopback interface: "
                                        + ifc.getDisplayName());
                                result = address;
                            }
                        }
                    }
                    // @formatter:on
                }
            }
        } catch (IOException ex) {
            log.error("Cannot get first non-loopback address", ex);
        }

        if (result != null) {
            return result;
        }

        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            log.warn("Unable to retrieve localhost");
        }

        return null;
    }

    /**
     * for testing
     */
    boolean ignoreAddress(InetAddress address) {
        boolean isUseOnlySiteLocalInterfaces = false;
        if (isUseOnlySiteLocalInterfaces && !address.isSiteLocalAddress()) {
            log.trace("Ignoring address: " + address.getHostAddress());
            return true;
        }

//        for (String regex : this.properties.getPreferredNetworks()) {
//            if (!address.getHostAddress().matches(regex) && !address.getHostAddress().startsWith(regex)) {
//                log.trace("Ignoring address: " + address.getHostAddress());
//                return true;
//            }
//        }
        return false;
    }

    /**
     * for testing
     */
    boolean ignoreInterface(String interfaceName) {
//        for (String regex : this.properties.getIgnoredInterfaces()) {
//            if (interfaceName.matches(regex)) {
//                log.trace("Ignoring interface: " + interfaceName);
//                return true;
//            }
//        }
        return false;
    }

    public HostInfo convertAddress(final InetAddress address) {
        HostInfo hostInfo = new HostInfo();
        Future<String> result = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return address.getHostName();
            }
        });

        String hostname;
        try {
            hostname = result.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.info("Cannot determine local hostname");
            hostname = "localhost";
        }
        hostInfo.setHostname(hostname);
        hostInfo.setIpAddress(address.getHostAddress());
        return hostInfo;
    }

    public static int getIpAddressAsInt(String host) {
        return new HostInfo(host).getIpAddressAsInt();
    }

    public static class HostInfo {
        public boolean override;
        private String ipAddress;
        private String hostname;

        public HostInfo(String hostname) {
            this.hostname = hostname;
        }

        public HostInfo() {
        }

        public int getIpAddressAsInt() {
            InetAddress inetAddress = null;
            String host = this.ipAddress;
            if (host == null) {
                host = this.hostname;
            }
            try {
                inetAddress = InetAddress.getByName(host);
            } catch (final UnknownHostException e) {
                throw new IllegalArgumentException(e);
            }
            return ByteBuffer.wrap(inetAddress.getAddress()).getInt();
        }

        public boolean isOverride() {
            return override;
        }

        public void setOverride(boolean override) {
            this.override = override;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }
    }

}
