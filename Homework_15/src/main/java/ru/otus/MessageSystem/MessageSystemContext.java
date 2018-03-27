package ru.otus.MessageSystem;

public class MessageSystemContext {

    private final MessageSystem messageSystem;

    private Address dbAddress;
    private Address signInAddress;
    private Address adminAddress;

    public MessageSystemContext(MessageSystem messageSystem, String db) {
        this.messageSystem = messageSystem;
        dbAddress = new Address(db);
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public Address getDbAddress() {
        return dbAddress;
    }

    public void setDbAddress(Address dbAddress) {
        this.dbAddress = dbAddress;
    }

    public Address getSignInAddress() {
        return signInAddress;
    }

    public void setSignInAddress(Address signInAddress) {
        System.out.println(signInAddress);
        this.signInAddress = signInAddress;
    }

    public Address getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(Address adminAddress) {
        System.out.println(adminAddress);
        this.adminAddress = adminAddress;
    }
}
