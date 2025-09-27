# Household Manager Android App

A comprehensive Android application for managing household activities, bills, tasks, and groceries with user permissions and admin controls.

## Features

### 🏠 House Management
- Set up your house with name and type (Apartment, House, Condo, Townhouse)
- Admin-controlled user permissions
- Multi-user household support

### 💰 Bill Management
- **Fixed Rate Bills**: Set up recurring bills with automatic monthly entries
- **Manual Entry**: Add one-time bills as needed
- **Payment Reminders**: Configurable reminder notifications before due dates
- **Payment Tracking**: Mark bills as paid with timestamps
- **Bill History**: View all paid and unpaid bills

### 📋 Task System
- **Task Categories**: Renovation, Maintenance, Shopping, Cleaning, Organization, Other
- **Priority Levels**: High, Medium, Low priority tasks
- **Assignment**: Assign tasks to specific household members
- **Due Dates**: Set and track task deadlines
- **Status Tracking**: TODO, In Progress, Completed, Cancelled
- **Overdue Alerts**: Automatic detection of overdue tasks

### 🛒 Grocery & Fridge Management
- **Inventory Tracking**: Keep track of all grocery items
- **Storage Locations**: Fridge, Freezer, Pantry, Counter, Cabinet
- **Expiry Monitoring**: Track best-before dates with alerts
- **Categories**: Fruits, Vegetables, Dairy, Meat, Seafood, Grains, Beverages, etc.
- **Consumption Tracking**: Mark items as consumed
- **Meal Planning**: Plan meals and link to grocery items

### 👥 User Management
- **Admin Permissions**: Root admin controls user access
- **Role-based Access**: Admin and Member roles
- **User Profiles**: Name, email, and activity tracking
- **Household Membership**: Users can belong to multiple houses

## Technical Architecture

### 🏗️ Architecture
- **MVVM Pattern**: Model-View-ViewModel architecture
- **Repository Pattern**: Data abstraction layer
- **Dependency Injection**: Hilt for dependency management
- **Reactive Programming**: Kotlin Coroutines and Flow

### 🗄️ Database
- **Room Database**: Local SQLite database with Room ORM
- **Type Converters**: Custom converters for complex data types
- **Relationships**: Proper entity relationships and foreign keys
- **Migration Support**: Database versioning and migration

### 🎨 UI/UX
- **Material Design 3**: Modern Material Design components
- **Jetpack Compose**: Declarative UI framework
- **Navigation**: Type-safe navigation with Compose Navigation
- **Theming**: Dynamic theming with Material 3 color schemes

### 📱 Key Components
- **Dashboard**: Overview of all household activities
- **House Setup**: Initial configuration screen
- **Bills Screen**: Comprehensive bill management
- **Tasks Screen**: Task creation and management
- **Groceries Screen**: Inventory and meal planning
- **Settings Screen**: User and house management

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Kotlin 1.9.10+
- Android SDK 24+ (target SDK 34)
- Gradle 8.1.4+

### Installation
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run on device or emulator

### Build Configuration
```gradle
android {
    compileSdk 34
    defaultConfig {
        minSdk 24
        targetSdk 34
    }
}
```

## Database Schema

### Core Entities
- **User**: User accounts with admin permissions
- **House**: House information and settings
- **HouseUser**: Many-to-many relationship between users and houses
- **Bill**: Bill management with recurring support
- **Task**: Task system with categories and priorities
- **GroceryItem**: Inventory management with expiry tracking
- **MealPlan**: Meal planning with ingredient linking

### Key Relationships
- Users can belong to multiple houses
- Bills are created by users
- Tasks can be assigned to users
- Grocery items are added by users
- Meal plans link to grocery items

## Features in Detail

### Bill Management
- **Fixed Rate**: Automatically generate monthly bills
- **Manual Entry**: One-time bill creation
- **Reminders**: Configurable days before due date
- **Recurring**: Support for monthly, quarterly, yearly bills
- **Payment Tracking**: Mark as paid with timestamps

### Task System
- **Categories**: Organized task types
- **Priorities**: Visual priority indicators
- **Assignment**: User assignment with notifications
- **Due Dates**: Deadline tracking with overdue alerts
- **Status Workflow**: Complete task lifecycle management

### Grocery Management
- **Inventory**: Complete item tracking
- **Expiry Alerts**: Proactive expiry notifications
- **Storage**: Multiple storage location support
- **Consumption**: Track item usage
- **Meal Planning**: Link meals to ingredients

## Future Enhancements

### Planned Features
- **Push Notifications**: Real-time alerts and reminders
- **Cloud Sync**: Multi-device synchronization
- **Expense Analytics**: Spending insights and reports
- **Shopping Lists**: Generate lists from meal plans
- **Photo Support**: Attach photos to tasks and items
- **Export/Import**: Data backup and sharing
- **Widgets**: Home screen widgets for quick access
- **Dark Mode**: Enhanced dark theme support

### Integration Possibilities
- **Calendar Integration**: Sync with device calendar
- **Shopping Apps**: Integration with grocery delivery services
- **Banking APIs**: Automatic bill detection
- **Smart Home**: IoT device integration
- **Voice Commands**: Voice-controlled task creation

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and questions, please open an issue in the GitHub repository.

---

**Household Manager** - Making household management simple and efficient for modern families.