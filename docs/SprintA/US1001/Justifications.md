We divided the questionnaire in different aggregates in order to not delete the whole just to delete a part of the questionnaire (if everything was in the same aggregate we would have to delete an entire questionnaire to delete a question)
E.g in case we wanted to delete a Section, if they were in the same aggregate we would have to delete the questionnaire also. Since they are separated from each other we can safely delete any section without deleting the whole questionnaire.

Since the Obligatoriness value object is simple and has the same domain rules in both aggregates (Question and Section) we shared the value object by putting it outside both aggregates and relating each aggregate roots to the value object.

Date of birth of a customer will be an instance of LocalDate, as LocalDate already has the needed domain rules.

Role will be an interface which all users will implement. This is done to simplify the permissions of each user and its management which is done by the Administrator.

Payment and Shipment are simple value objects of Order, because the client stated that those concepts are out of scope for this prototype and are to be implemented as an extension point for further development. [https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=15646](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=15646)

AGVDock is the root of the aggregate. This is done so that we relate the AGVDocks with the warehouses.

Warehouse and its components are in the same aggregate so that we carry the consistency throughout the management of the facilities.