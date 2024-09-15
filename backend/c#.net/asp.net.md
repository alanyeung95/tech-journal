## DataAnnotations

could be used for data validation
```
using System.ComponentModel.DataAnnotations;

public class User
{
    [Required(ErrorMessage = "User ID is required.")]
    [Display(Name = "User ID")]
    public int UserId { get; set; }

    [Required]
    [StringLength(100, MinimumLength = 6, ErrorMessage = "Username must be between 6 and 100 characters long.")]
    [Display(Name = "Username")]
    public string Username { get; set; }

    [Required]
    [EmailAddress(ErrorMessage = "Invalid Email Address.")]
    [Display(Name = "Email Address")]
    public string Email { get; set; }

    [Required]
    [DataType(DataType.Password)]
    [StringLength(100, MinimumLength = 8, ErrorMessage = "Password must be at least 8 characters long.")]
    [Display(Name = "Password")]
    public string Password { get; set; }

    [DataType(DataType.Date)]
    [Display(Name = "Birth Date")]
    public DateTime BirthDate { get; set; }
}
```

## Data return from controller

Transferring data from a controller to a view in ASP.NET MVC can be accomplished using several methods. Each method serves different scenarios and requirements. Here are the main ways to transfer data:

### 1. **ViewData**
- **Type**: `ViewDataDictionary` provided by the `ControllerBase` class.
- **Usage**: Useful for transferring loosely-typed data from a controller to a view. It’s a dictionary object that can hold any type of data, but requires type casting when retrieving the data in the view.
- **Example**:
  ```csharp
  public ActionResult Index()
  {
      ViewData["Message"] = "Hello from ViewData!";
      return View();
  }
  ```
  In the view:
  ```html
  <div>@ViewData["Message"]</div>
  ```

### 2. **ViewBag**
- **Type**: `dynamic` property provided by the `ControllerBase` class.
- **Usage**: Similar to `ViewData` but does not require type casting due to its dynamic nature. Useful for passing data that does not involve a complex business model.
- **Example**:
  ```csharp
  public ActionResult Index()
  {
      ViewBag.Message = "Hello from ViewBag!";
      return View();
  }
  ```
  In the view:
  ```html
  <div>@ViewBag.Message</div>
  ```

### 3. **Model**
- **Type**: Strongly-typed views that are tightly bound to a model.
- **Usage**: Best for transferring complex data and recommended for most scenarios due to strong typing, which helps in reducing errors at compile time.
- **Example**:
  ```csharp
  public ActionResult Index()
  {
      var model = new MyViewModel
      {
          Message = "Hello from Model!"
      };
      return View(model);
  }
  ```
  In the view:
  ```html
  @model MyViewModel
  <div>@Model.Message</div>
  ```
