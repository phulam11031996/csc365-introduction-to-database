import java.util.HashMap;
import java.util.HashSet;

public class Ownership {

    public HashMap<String, Boolean> ownerCardNumbers;
    public HashSet<Integer> ownerCustomerIds;

    public Ownership(HashMap<String, Boolean> ownerCardNumbers, HashSet<Integer> ownerCustomerId) {
        this.ownerCardNumbers = ownerCardNumbers;
        this.ownerCustomerIds = ownerCustomerId;
    }

    // getters
    public HashMap<String, Boolean> getOwnerCardNumbers() {
        return this.ownerCardNumbers;
    }

    public HashSet<Integer> getOwnnerCustomerIds() {
        return this.ownerCustomerIds;
    }

    // methods 
    public void addCustomerId(int customerId) {
        this.ownerCustomerIds.add(customerId);
    }

    public void addCCNumber(String ccNumber, boolean isActive) {
        this.ownerCardNumbers.put(ccNumber, isActive);
    }
    
}