package com.example.rhymefinder.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//The hard-coded list of poems in the Home screen
@Parcelize
data class poems(
    val poem: String, val rhyme: String, val poet: String
) : Parcelable

val poemList = listOf(
    poems(
        "در سخن گفتن خطای جاهلان پیدا شود\n" + "\n" + "تیر کج چون از کمان بیرون دود رسوا شود",
        "رسوا",
        "صائب تبریزی"
    ),
    poems(
        "هر کجا عشق آید و ساکن شود\n" + "\n" + "هر چه نا ممکن بود ممکن شود",
        "ممکن",
        "مولانا"
    ),
    poems(
        "ترا دیدم و یوسف را شنیدم\n" + "\n" + "شنیدن کی بود مانند دیدن",
        "دیدن",
        "جامی"
    ),
    poems(
        "دل ز پر گفتن بمیرد در بدن\n" + "\n" + "گر چه گفتارش بود در عدن",
        "عدن",
        "عطار"
    ),
    poems(
        "امشب تو را به خوبی نسبت به ماه کردم\n" + "\n" + "تو خوب تر ز ماهی من اشتباه کردم",
        "اشتباه",
        "فروغ بسطامی"
    ),
    poems(
        "در این فکرم که خواهی ماند با من مهربان یا نه\n" + "\n" + "به من کم میکنی لطفی که داری این زمان یا نه",
        "زمان",
        "وحشی بافقی"
    ),
    poems(
        "آب دریا را اگر نتوان کشـید\n" + "\n" + "هم به قدر تشنگی باید چشید",
        "چشید",
        "مولانا"
    ),
    poems(
        "به صد سال یک دوست آید به دست\n" + "\n" + "به یک روز توان کرد دشمن به شصت",
        "دست",
        "سعدی"
    ),
    poems(
        "طاعت آن نیست که بر خاک نهی پیشانی\n" + "\n" + "صدق پیش آر که ابلیس بسی کرد سجود",
        "سجود",
        "سعدی"
    ),
    poems(
        "ما چو ناییم و نوا در ما ز توست\n" + "\n" + "ما چو کوهیم و صدا در ما ز توست",
        "صدا",
        "مولانا"
    ),
    poems(
        "بزرگی سراسر به گفتار نیست\n" + "\n" + "دو صد گفته چون نیم کردار نیست",
        "کردار",
        "فردوسی"
    ),
    poems(
        "به زیورها بیارایند مردم خوبرویان را\n" + "\n" + "تو سیمین تن چنان خوبی که زیورها بیارایی",
        "بیارایی",
        "حافظ"
    ),
    poems(
        "کلید در دوزخ است آن نماز\n" + "\n" + "که در چشم مردم گزاری دراز",
        "دراز",
        "سعدی"
    ),
    poems(
        "سرمای قوس تک تک دندان ندیده ای\n" + "\n" + "بر عیال و سفره بی نان ندیده ای",
        "نان",
        "صائب تبریزی"
    ),
    poems(
        "جان پدر تو سفره بی نان ندیده ای\n" + "\n" + "سرمای قوس و تک تک دندان ندیده ای",
        "دندان",
        "صائب تبریزی"
    ),
    poems(
        "بر مال و جمال خویش غره مشو\n" + "\n" + "کان را به شبی برند و این را به تبی",
        "تبی",
        "اسعد گرگانی"
    ),
    poems(
        "این جهان زندان و ما زندانیان\n" + "\n" + "حفره کن زندان و خود را وا راهان",
        "راهان",
        "مولانا"
    ),
    poems(
        "دشمن خود را نباید زد تبر\n" + "\n" + "گر توانی کشت او را با شکر",
        "شکر",
        "سعدی"
    ),
    poems(
        "تا تو مراد من دهی کشته مرا فراق تو\n" + "\n" + "تا تو به داد من رسی من به خدا رسیده ام",
        "رسیده ام",
        "رهی معیری"
    ),
    poems(
        "گر عمارت را بری بر آسمان\n" + "\n" + "عاقبت زیر زمین گردی نهان",
        "نهان",
        "عطار"
    ),
    poems(
        "از تواضع کم نگردد رتبه گردن کشان\n" + "\n" + "نیست عیبی گر بود شمشیر لنگردار کج",
        "کج",
        "صائب تبریزی"
    ),
    poems(
        "در همه آفاق کس بی مرگ نیست\n" + "\n" + "وین عجایب بین که کس را برگ نیست", "برگ", "عطار"
    ),
    poems(
        "کم گوی و به جز مصلحت خویش مگوی\n" + "\n" + "چیزی که نپرسند تو خود پیش مگوی",
        "پیش",
        "بابا افضل"
    ),
    poems(
        "گر تو گرفتارم کنی من با گرفتاری خوشم\n" + "\n" + "داروی دردم گر تویی در اوج بیماری خوشم",
        "بیماری",
        "مولانا"
    ),
    poems(
        "شبی بیدار دار آخر خدا را\n" + "\n" + "چو صد شب داشتی نفس وهوا را",
        "هوا",
        "عطار"
    ),
    poems(
        "هر که بود عاشق خود پنج نشان دارد\n" + "\n" + "سخت دل و سست قدم و کاهل و بیکار و ترش",
        "ترش",
        "مولوی"
    ),
    poems(
        "گرمی خورشید ز عیسی بپرس\n" + "\n" + "خوبی یوسف ز زلیخا بپرس",
        "زلیخا",
        "وحشی بافقی"
    ),
    poems(
        "تا خدا یار است با سلطان مپیچ\n" + "\n" + "گر خدا برگشت صد سلطان به هیچ",
        "هیچ",
        "سعدی"
    ),
    poems(
        "گفتار بسیار نه از نغزیست\n" + "\n" + "ولوله طبل ز بی مغزییست",
        "مغزییست",
        "جامی"
    ),
    poems(
        "دوست را کس به یک بدی نفروخت\n" + "\n" + "بهر کیکی گلیم نتوان سوخت",
        "سوخت",
        "سنایی غزنوی"
    )
)