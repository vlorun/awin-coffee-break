# Awin CoffeeBreak

## Next Steps
**Controller/ Interface:**
- Additional refactoring is required to decouple the formatting from the controller response object
- the notification service ideally should be implemented with a strategy pattern,
therefore requiring additional refactoring of the StaffMemberService as well as the entity.
- the full CRUD implementation for coffee preferences should be developed

**Entities/ Domain models:**
- Currently, CoffeeBreakPreference has too many responsibilities and contains too much domain
not exclusive to its functionality; the super "type" and sub "subtype" should be
refactored into coffee menu items in a composite pattern combining options and preference details.
- Take another look at the naming convention for preferences and minimise the amount of
primitive types being passing into the constructors( this will help upstream inside the services and controller too.)

**Tests:**
- Improving test coverage.

**Setup:**
Configure a dockerised container setup and decide on alternative database implementation than the current
implimentation. 
    

