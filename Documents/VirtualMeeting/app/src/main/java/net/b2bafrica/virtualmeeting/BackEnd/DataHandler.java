package net.b2bafrica.virtualmeeting.BackEnd;

public class DataHandler {
    private String[] countries;
    private String[] industries;
    private String[] targetAudience;
    private String[] meetingNumber;
    private String[] meetingType;
    private String[] worldCountries;

    public String[] returnTypeMeeting(){
        meetingType = new String[]{
                "Select Type of Meeting",
                "Virtual Meeting", "Site Visits","Personal Meetings"
        };
        return meetingType;
    }
    public String[] returnTargetAudience(){
        targetAudience = new String[]{
                "Select Audience",
                "Distributors","Wholesalers","Joint Venture","Importer"
        };
        return targetAudience;
    }

    public String[] returnMeetingNo(){
        meetingNumber = new String[]{
                "Select Number of Meetings",
                "5-8","8-12","12-16","16-22"
        };
        return meetingNumber;
    }

    public String[] returnWorldCountries(){
        countries = new String[]{
                "Select Country",
                "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda",
                "Argentina", "Armenia", "Australia", "Austria", "Austrian Empire", "Azerbaijan",
                "Baden*", "Bahamas, The", "Bahrain", "Bangladesh",
                "Barbados", "Bavaria*", "Belarus", "Belgium", "Belize", "Benin (Dahomey)",
                "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei",
                "Brunswick and Lüneburg", "Bulgaria", "Burkina Faso (Upper Volta)", "Burma",
                "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Cayman Islands, The Central African Republic",
                "Central American Federation*", "Chad", "Chile", "China", "Colombia", "Comoros",
                "Congo Free State, The", "Costa Rica", "Cote d’Ivoire (Ivory Coast)", "Croatia", "Cuba", "Cyprus", "Czechia",
                "Czechoslovakia", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica",
                "Dominican Republic", "Duchy of Parma, The*",
                "East Germany (German Democratic Republic)", "Ecuador", "Egypt", "El Salvador",
                "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini",
                "Ethiopia", "Federal Government of Germany (1848-49)*", "Fiji", "Finland", "France", "Gabon", "Gambia, The", "Georgia", "Germany",
                "Ghana", "Grand Duchy of Tuscany, The*", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Hanover*",
                "Hanseatic Republics*", "Hawaii*", "Hesse*", "Holy See", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq",
                "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kingdom of Serbia/Yugoslavia*", "Kiribati",
                "Korea", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Lew Chew (Loochoo)*", "Liberia", "Libya",
                "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar",
                "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
                "Mauritania", "Mauritius", "Mecklenburg-Schwerin*",
                "Mecklenburg-Strelitz*", "Mexico", "Micronesia", "Moldova", "Monaco",
                "Mongolia", "Montenegro", "Morocco", "Mozambique",
                "Namibia", "Nassau*", "Nauru", "Nepal", "Netherlands, The", "New Zealand",
                "Nicaragua", "Niger", "Nigeria", "North German Confederation*",
                "North German Union*", "North Macedonia", "Norway", "Oldenburg*",
                "Oman", "Orange Free State*", "Pakistan", "Palau", "Panama", "Papal States*", "Papua New Guinea", "Paraguay", "Peru", "Philippines",
                "Piedmont-Sardinia*", "Poland", "Portugal", "Qatar", "Republic of Genoa*", "Republic of Korea (South Korea)", "Republic of the Congo",
                "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
                "Sao Tome and Principe", "Saudi Arabia", "Schaumburg-Lippe*", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore",
                "Slovakia", "Slovenia", "Solomon Islands, The", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname",
                "Sweden", "Switzerland", "Syria", "Tajikistan", "Tanzania", "Texas*", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago",
                "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Two Sicilies*", "Uganda", "Ukraine", "Union of Soviet Socialist Republics*",
                "United Arab Emirates, The", "United Kingdom, The", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Württemberg*",
                "Yemen", "Zambia", "Zimbabwe"
        };
        return countries;
    }
    public String[] returnCountries(){
        countries = new String[]{
                "Select Interested Market",
                "Algeria","Angola","Benin",
                "Botswana","Burkina Faso",
                "Burundi","Cabo Verde","Cameroon",
                "Central African Republic (CAR)",
                "Chad","Comoros","Congo, Democratic Republic of the Congo",
                "Republic of the Cote d'Ivo","Djibouti","Egypt","Equatorial Guinea",
                "Eritrea","Eswatini (formerly Swaziland)",
                "Ethiopia","Gabon","Gambia","Ghana",
                "Guinea","Guinea-Bissau","Kenya",
                "Lesotho","Liberia","Libya","Madagascar",
                "Malawi","Mali","Mauritania","Mauritius",
                "Morocco","Mozambique","Namibia","Niger",
                "Nigeria","Rwanda","Sao Tome and Principe",
                "Senegal","Seychelles","Sierra Leone",
                "Somalia","South Africa","South Sudan",
                "Sudan","Tanzania","Togo","Tunisia","Uganda",
                "Zambia","Zimbabwe"
        };
        return countries;
    }

    public String[] returnIndustries(){
        industries = new String[]{
                "Select Industry",
                "Accounting","Airlines/Aviation","Animation","Apparel/Fashion","Architecture/Planning",
                "Arts/Crafts","Automotive","Aviation/Aerospace","Banking/Mortgage","Biotechnology/Greentech",
                "Broadcast Media","Building Materials","Business Supplies/Equipment","Chemicals",
                "Civil Engineering","Commercial Real Estate","Computer Hardware/Software",
                "Computer Engineering","Construction","Consumer Electronics","Consumer Goods & Services",
                "Cosmetics","Dairy","Defense/Space","Design","E-Learning","Education Management",
                "Electrical/Electronic Manufacturing","Farming","Financial Services","Fishery","Food Production",
                "Food/Beverages","Furniture","Glass/Ceramics/Concrete","Graphic Design/Web Design",
                "Health/Fitness","Higher Education","Hospital/Health Care",
                "Hospitality","Human Resources/HR","Import/Export","Industrial Automation","Information Services","Information Technology/IT","Insurance",
                "Leisure/Travel","Logistics/Procurement","Luxury Goods/Jewelry","Machinery","Maritime","Market Research",
                "Marketing/Advertising/Sales","Mechanical or Industrial Engineering","Media Production","Medical Equipment",
                "Medical Practice","Military Industry","Mining/Metals","Motion Pictures/Film","Museums/Institutions","Music",
                "Newspapers/Journalism","Non-Profit Organization","Oil/Energy/Solar/Greentech","Online Publishing","Packaging/Containers",
                "Paper/Forest Products","Pharmaceuticals","Plastics","Primary/Secondary Education","Printing","Real Estate/Mortgage",
                "Recreational Facilities/Services","Renewables/Environment","Retail Industry","Security","Sporting Goods","Supermarkets",
                "Telecommunications","Textiles","Utilities","Veterinary","Warehousing","Wholesale","Wine/Spirits","Other Industry"
        };
        return  industries;
    }
}
