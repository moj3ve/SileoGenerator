# SileoGenerator
The Sileo Depiction System is hard and time consuming to generate manually, this tool makes it easier!
 
# Using this tool
- Download the latest from [the releases page](https://github.com/ConorTheDev/SileoGenerator/releases)
- Run it from the commandline with `java -jar SileoGenerator-1.0.jar create`
    - Note: not specifying the create parameter will show a help menu
- Answer the questions it asks and you're done! It'll be outputted to ./PackageName.json

# Features
- Fast Depiction Generation
- Made in Java, no need to install Python!
- Screenshot Support
- Made with ❤️

# To-Do
- [ ] Make a GUI
- [ ] Support for plugins so people can add more questions
- [ ] Generate Sileo-Featured.json

# Example JSON output
```json
{
    "minVersion": "0.1",
    "tabs": [{
        "tabname": "Details",
        "views": [
            {
                "markdown": "## ModernPower\nModernPower best tweak",
                "class": "DepictionMarkdownView"
            },
            {
                "itemCornerRadius": 9,
                "itemSize": "{187.5, 406}",
                "screenshots": [
                    {
                        "fullSizeURL": "https://repo.conorthedev.com/img/modernpower-screenshot-1.png",
                        "accessibilityText": "Screenshot",
                        "url": "https://repo.conorthedev.com/img/modernpower-screenshot-1.png"
                    },
                    {
                        "fullSizeURL": "https://repo.conorthedev.com/img/modernpower-screenshot-2.png",
                        "accessibilityText": "Screenshot",
                        "url": "https://repo.conorthedev.com/img/modernpower-screenshot-2.png"
                    },
                    {
                        "fullSizeURL": "https://repo.conorthedev.com/img/modernpower-screenshot-3.png",
                        "accessibilityText": "Screenshot",
                        "url": "https://repo.conorthedev.com/img/modernpower-screenshot-3.png"
                    },
                    {
                        "fullSizeURL": "https://repo.conorthedev.com/img/modernpower-screenshot-4.png",
                        "accessibilityText": "Screenshot",
                        "url": "https://repo.conorthedev.com/img/modernpower-screenshot-4.png"
                    },
                    {
                        "fullSizeURL": "https://repo.conorthedev.com/img/modernpower-screenshot-5.png",
                        "accessibilityText": "Screenshot",
                        "url": "https://repo.conorthedev.com/img/modernpower-screenshot-5.png"
                    }
                ]
            }
        ]
    }],
    "tintColor": "#f75454"
}
```