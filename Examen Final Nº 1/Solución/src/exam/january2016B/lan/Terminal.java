package exam.january2016B.lan;

/**
 *
 * @author jvelez
 */
public class Terminal implements Host {    
    final String IP;
    final String MAC;

    public Terminal(String macAddress, String IP) {
        this.MAC = macAddress;
        this.IP = IP;
    }
    
    @Override
    public String getIP() {
        return IP;
    }
    
}
