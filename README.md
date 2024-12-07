# **Asciify - Image to ASCII Art Converter**

Asciify is a command-line tool to convert images into stunning ASCII art. You can adjust the width of the rendered output and optionally save it as an HTML file.

## **Features**
- Convert images into ASCII art directly from the command line.
- Adjust the width of the ASCII output for more detail.
- Optionally generate an HTML file to view the ASCII art in your browser.

## **Installation**

#### Note: you can use already built jar file in `jar` folder.

But if you want to build it yourself then here is a step-by-step example.
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/asciify.git
   cd asciify
   ```
2. Run nix-shell to make environment to build the project (if you don't have yet) and build it:
   ```bash
   nix-shell
   mvn clean package
   ```

## **Usage**
I will use already built jar file which is located in `jar` folder.
Run the tool with the following command:
   ```bash
   java -jar jar/asciify.jar <imagePath> [options]
   ```
Options:
  - `<imagePath>`: (Required) The path to the image file to convert.
  - `-w, --width <width>`: (Optional) Specify the width of the ASCII art output. Default is 150.
  - `-o, --output <output>`: (Optional) Specify the name of the HTML file to save the ASCII art.

## **Examples**

1. Convert an image and display ASCII art in the terminal
   ```bash
   java -jar jar/asciify.jar images/eye.jpeg
   ```
2. Convert an image with a custom width
   ```bash
   java -jar jar/asciify.jar my-image.jpg -w 200
   ```
3. Convert an image and save the output as an HTML file
   ```bash
   java -jar jar/asciify.jar my-image.jpg -o ascii-art.html
   ```
