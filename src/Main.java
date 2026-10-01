public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && "--demo".equals(args[0])) {
            runDemo();
        } else {
            System.out.println("Use '--demo' argument to run the Bridge pattern demonstration checks T1-T7.");
        }
    }

    private static void runDemo() {
        int passed = 0;
        int total = 7;

        Channel email = new EmailChannel();
        Channel sms = new SmsChannel();
        Channel push = new PushChannel();

        String msgReminder = "Submit Assignment 3";
        String msgUrgent = "Server Connection Lost";

        Reminder r1 = new Reminder("NOTIF-R1", msgReminder, email);
        String resT1 = r1.execute();
        String expT1 = "EMAIL envelope: Submit Assignment 3";
        boolean passT1 = expT1.equals(resT1);
        printCheckResult("T1", passT1, "Reminder + EmailChannel", resT1, expT1);
        if (passT1) passed++;

        Reminder r2 = new Reminder("NOTIF-R2", msgReminder, sms);
        String resT2 = r2.execute();
        String expT2 = "SMS: Submit Assignment 3";
        boolean passT2 = expT2.equals(resT2);
        printCheckResult("T2", passT2, "Reminder + SmsChannel", resT2, expT2);
        if (passT2) passed++;

        UrgentAlert u1 = new UrgentAlert("NOTIF-U1", msgUrgent, email);
        String resT3 = u1.execute();
        String expT3 = "EMAIL envelope: [URGENT] Server Connection Lost";
        boolean passT3 = expT3.equals(resT3);
        printCheckResult("T3", passT3, "UrgentAlert + EmailChannel", resT3, expT3);
        if (passT3) passed++;

        UrgentAlert u2 = new UrgentAlert("NOTIF-U2", msgUrgent, sms);
        String resT4 = u2.execute();
        String expT4 = "SMS: [URGENT] Server Connection Lost";
        boolean passT4 = expT4.equals(resT4);
        printCheckResult("T4", passT4, "UrgentAlert + SmsChannel", resT4, expT4);
        if (passT4) passed++;

        Reminder switchObj = new Reminder("NOTIF-SW1", msgReminder, email);
        Reminder refBefore = switchObj;
        String resBefore = switchObj.execute();
        String idBefore = switchObj.getId();
        String msgBefore = switchObj.getMessage();

        switchObj.setImplementation(sms);
        Reminder refAfter = switchObj;
        String resAfter = switchObj.execute();

        boolean sameObject = (refBefore == refAfter);
        boolean stateUnchanged = idBefore.equals(switchObj.getId()) && msgBefore.equals(switchObj.getMessage());
        boolean behaviorChanged = expT1.equals(resBefore) && expT2.equals(resAfter);
        boolean passT5 = sameObject && stateUnchanged && behaviorChanged;

        System.out.printf("%s %s | sameObject=%b | stateUnchanged=%b%n  before=%s | after=%s%n",
                "T5", passT5 ? "PASS" : "FAIL", sameObject, stateUnchanged, resBefore, resAfter);
        if (passT5) passed++;

        Reminder r3 = new Reminder("NOTIF-R3", msgReminder, push);
        String resT6 = r3.execute();
        String expT6 = "PUSH notification: Submit Assignment 3";
        boolean passT6 = expT6.equals(resT6);
        printCheckResult("T6", passT6, "Reminder + PushChannel", resT6, expT6);
        if (passT6) passed++;

        UrgentAlert u3 = new UrgentAlert("NOTIF-U3", msgUrgent, push);
        String resT7 = u3.execute();
        String expT7 = "PUSH notification: [URGENT] Server Connection Lost";
        boolean passT7 = expT7.equals(resT7);
        printCheckResult("T7", passT7, "UrgentAlert + PushChannel", resT7, expT7);
        if (passT7) passed++;

        System.out.printf("SUMMARY: %d/%d PASS%n", passed, total);
    }

    private static void printCheckResult(String id, boolean pass, String details, String actual, String expected) {
        if (pass) {
            System.out.printf("%s PASS | %s | result=%s%n", id, details, actual);
        } else {
            System.out.printf("%s FAIL | %s | actual=%s | expected=%s%n", id, details, actual, expected);
        }
    }
}