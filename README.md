# DownloadsOrganizer
A simple **Downloads** folder organizer for Windows and Linux based systems.

## Default Downloads Path
* ### Windows: `C:\Users\userName\Downloads\`
* ### Linux: `/home/userName/Downloads/`

#### All files in **Downloads** folder will be organized in the following directory structure:
* **Pictures**  `{.jpg, .jpeg, .png, .gif, .bmp}`
* **Software**  `{.exe, .jar, .bat, .py, .sh, .pl, .msi, .ini, .run, .out, .deb, .rpm}`
* **Archives**  `{.zip, .tgz, .tar.gz, .tar, .bz2, .7z, .rar, .pkg}`
* **Videos**  `{.mp4, .avi, .flv, .mkv, .wmv, .3gp, .mpeg, .mpg, .h264}`
* **Audios** `{.mp3, .wav, .aif, .mpa, .wma, .wpl}`
* **Docs**  `{$RemainingFiles}`

### Prerequisites

* JRE 8

### Installing
Download `.jar` file of latest version from **releases**.

### Run
Run `DownloadsOrganizer.jar` file

### Example
#### Before:
```
Downloads
│   ├── pic1.jpg
│   ├── pic2.gif
│   ├── chrome.exe
│   ├── executable.msi
│   ├── sheets.zip
│   ├── Game_of_thrones_S0801.mp4
│   ├── Seinfield_S0101.flv
│   ├── Not Afraid_Eminem.mp3
│   ├── book.pdf
│   ├── file.docx
│   ├── presentation.ppt
│   ├── xFolder
│   │   └── xFile  
```

#### After:
```
Downloads
│   ├── Archives
│   │   └── sheets.zip
|   |
│   ├── Audios
│   │   └── Not Afraid_Eminem.mp3
|   |
│   ├── Docs
│   │   ├── book.pdf
│   │   ├── file.docx
│   │   └── presentation.ppt
|   |
│   ├── Pictures
│   │   ├── pic1.jpg
│   │   └── pic2.gif
|   |
│   ├── Software
│   │   ├── chrome.exe
│   │   └── executable.msi
|   |
│   ├── Videos
│   │   ├── Game_of_thrones_S0801.mp4
│   │   └── Seinfield_S0101.flv
│   ├── xFolder
│   │   └── xFile
```

#### NOTE:
Sub-directories inside **Downloads** won't be organized.

### License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
