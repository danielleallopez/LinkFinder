# LinkFinder
Application that lists all links present in a given website. I've used a MVP architecture, with help of Dagger2. It also contains some visual tests (work in progress).

#### Main View 
In the MainActivity, there's an EditText in which the user can input the website url. 

After tapping on the button, it validates that the string complies the Uri standard and tries to download its HTML content. The timeout is set to 20s.

#### List View 
The list view shows all the links found in the selected website whose protocol is either http or https.
