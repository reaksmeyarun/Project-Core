# Clean Architecture Project

## Overview

This repository implements a project following Clean Architecture principles. Clean Architecture promotes separation of concerns, making systems easier to manage, test, and scale. Each layer has distinct responsibilities, allowing for flexibility and maintainability in software development.

## Project Structure

The project is organized into several layers, each responsible for specific aspects of the application:

<img width="267" alt="image" src="https://github.com/user-attachments/assets/19e61a95-d784-4433-b0ad-259dcf80d4d3">

### 1. Adapters

The **Adapters** layer serves as an interface between the external world (like the web or other systems) and the application core. It translates incoming requests into a format that the application can process.

- **Controller**
  - **Responsibility**: Handles incoming HTTP requests and orchestrates responses. It acts as an intermediary between the user interface and the application logic.
  - **How It Works**: Controllers receive requests, validate input data, invoke the appropriate use cases, and return responses.
  - **Why It’s Needed**: It encapsulates web-specific details and keeps the application logic independent from the user interface.

- **DTO (Data Transfer Object)**
  - **Responsibility**: Structures data for transmission between different layers or external systems, ensuring data integrity and reducing the amount of data transferred.
  - **How It Works**: DTOs define the data structure required by the application layer and are used to transfer data between layers.
  - **Why It’s Needed**: It provides a clear contract for data exchange, minimizing changes to the internal data structure when integrating with external systems.

### 2. Application

The **Application** layer contains the business logic and application-specific use cases.

- **DTO**
  - **Responsibility**: Data Transfer Objects used specifically within the application layer to facilitate communication between use cases and controllers.
  - **How It Works**: Application DTOs may encapsulate data related to specific operations or business logic.
  - **Why It’s Needed**: It helps maintain a clean separation between the application layer and external layers, ensuring that internal logic remains insulated from changes in external interfaces.

- **UseCase**
  - **Responsibility**: Encapsulates a specific application action or business requirement, orchestrating interactions between various components to fulfill the request.
  - **How It Works**: Use cases are invoked by controllers and coordinate the flow of data and operations across entities and repositories.
  - **Why It’s Needed**: They serve as the application’s entry point, allowing for reusable, maintainable, and testable units of business logic.

### 3. Domain

The **Domain** layer is the core of the application, focusing on business logic and domain rules.

- **Entities**
  - **Responsibility**: Represents the core data model, encapsulating attributes and behaviors related to the domain.
  - **How It Works**: Entities contain business logic and ensure the integrity of the data they manage.
  - **Why It’s Needed**: It models real-world concepts, allowing the application to operate based on domain-specific rules and constraints.

- **Repository**
  - **Responsibility**: Defines the contract for data access and manipulation, providing an abstraction layer over the data source.
  - **How It Works**: Repositories handle data operations, such as querying, updating, and deleting entities. They decouple the application logic from the underlying data storage mechanism.
  - **Why It’s Needed**: It allows for easier testing and flexibility in changing data sources without affecting the application logic.

### 4. Infrastructure

The **Infrastructure** layer deals with the details of data persistence and external systems integration.

- **Mapper**
  - **Responsibility**: Maps data between different representations, such as between DTOs and entities.
  - **How It Works**: Mappers convert data from one structure to another, ensuring that the application can seamlessly translate between external and internal representations.
  - **Why It’s Needed**: It allows for flexibility in data handling and helps maintain a clear separation of concerns between layers.

- **Persistence**
  - **Responsibility**: Manages the actual data storage and retrieval mechanisms (e.g., databases, file systems).
  
  - **Repository** (under Persistence)
    - **Responsibility**: Provides concrete implementations of the repository interfaces defined in the domain layer.
    - **How It Works**: Repository implementations interact with the database or other storage systems to perform CRUD operations.
    - **Why It’s Needed**: It allows the application to manage data consistently and transparently, regardless of the underlying storage technology.

- **Service**
  - **Responsibility**: Contains logic for integrating with external systems (e.g., APIs, third-party services).
  - **How It Works**: Services facilitate communication between the application and external resources, ensuring data is correctly retrieved or sent.
  - **Why It’s Needed**: It abstracts external dependencies, making the application easier to test and modify.
