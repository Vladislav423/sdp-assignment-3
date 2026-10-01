# Role Map (Option B: Notifications)

**Base Commit Hash:** `3477597953d174067f10e9affd281036784fe495`

| Pattern Role | Class / Interface | Source Path |
| :--- | :--- | :--- |
| **Abstraction** | `Notification` | `src/Notification.java` |
| **Refined Abstraction 1 (A1)** | `Reminder` | `src/Reminder.java` |
| **Refined Abstraction 2 (A2)** | `UrgentAlert` | `src/UrgentAlert.java` |
| **Implementor** | `Channel` | `src/Channel.java` |
| **Concrete Implementor 1 (I1)**| `EmailChannel` | `src/EmailChannel.java` |
| **Concrete Implementor 2 (I2)**| `SmsChannel` | `src/SmsChannel.java` |
| **Concrete Implementor 3 (I3)**| `PushChannel` | `src/PushChannel.java` |
| **Client** | `Main` | `src/Main.java` |

### Key Structural Links
* **Bridge Reference:** `protected Channel channel;` in `src/Notification.java`
* **Execution Method:** `public String execute()` in `src/Notification.java`, `src/Reminder.java`, `src/UrgentAlert.java`
* **Implementation Switch:** `public void setImplementation(Channel channel)` in `src/Notification.java`
* **Runtime Switch Verification (T5):** Lines in `src/Main.java`

## Build and Run Commands

```powershell
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo