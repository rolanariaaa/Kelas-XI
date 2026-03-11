# 🎨 QUICK DESIGN GUIDE - Sedekah Yuk

## 📏 Design Standards

### Spacing System
```
XS: 4dp
SM: 8dp
MD: 12dp
LG: 16dp
XL: 20dp
XXL: 24dp
```

### Corner Radius
```
Small Cards: 12dp
Medium Cards: 16dp
Large Cards: 20dp
Buttons: 12-16dp
Chips: 20dp (pill shape)
```

### Elevation
```
Level 0: 0dp (flat)
Level 1: 2dp (subtle)
Level 2: 4dp (raised)
Level 3: 6dp (prominent)
Level 4: 8dp (modal)
Level 5: 12dp (FAB)
```

### Text Sizes
```
Display: 36sp (bold)
H1: 24sp (bold)
H2: 20sp (bold)
H3: 18sp (bold)
H4: 16sp (bold)
Body: 14sp (regular)
Body Small: 13sp (regular)
Caption: 12sp (regular)
Tiny: 11sp (regular)
Micro: 10sp (regular)
```

---

## 🎨 Color System

### Primary Green
```xml
<color name="green_50">#E8F5E9</color>
<color name="green_100">#C8E6C9</color>
<color name="green_200">#A5D6A7</color>
<color name="green_300">#81C784</color>
<color name="green_400">#66BB6A</color>
<color name="green_500">#4CAF50</color> <!-- Primary -->
<color name="green_600">#43A047</color>
<color name="green_700">#388E3C</color>
<color name="green_800">#2E7D32</color>
<color name="green_900">#1B5E20</color>
```

### Secondary Orange
```xml
<color name="orange_100">#FFE0B2</color>
<color name="orange_500">#FF9800</color> <!-- Secondary -->
<color name="orange_700">#F57C00</color>
```

### Accent Blue
```xml
<color name="blue_500">#2196F3</color>
<color name="blue_700">#1976D2</color>
```

### Accent Purple
```xml
<color name="purple_500">#9C27B0</color>
<color name="purple_700">#7B1FA2</color>
```

### Semantic Colors
```xml
<color name="success">#4CAF50</color>
<color name="warning">#FF9800</color>
<color name="error">#F44336</color>
<color name="info">#2196F3</color>
```

### Neutrals
```xml
<color name="black">#000000</color>
<color name="gray_900">#212121</color>
<color name="gray_800">#424242</color>
<color name="gray_700">#616161</color>
<color name="gray_600">#757575</color>
<color name="gray_500">#9E9E9E</color>
<color name="gray_400">#BDBDBD</color>
<color name="gray_300">#E0E0E0</color>
<color name="gray_200">#EEEEEE</color>
<color name="gray_100">#F5F5F5</color>
<color name="white">#FFFFFF</color>
```

---

## 📦 Common Components

### Material Card Template
```xml
<com.google.android.material.card.MaterialCardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:cardCornerRadius="16dp"
    app:cardElevation="4dp"
    app:cardBackgroundColor="@color/white">
    
    <!-- Content here -->
    
</com.google.android.material.card.MaterialCardView>
```

### Primary Button
```xml
<com.google.android.material.button.MaterialButton
    android:layout_width="match_parent"
    android:layout_height="56dp"
    android:text="Button Text"
    android:textColor="@color/white"
    android:textSize="16sp"
    android:textStyle="bold"
    app:backgroundTint="#4CAF50"
    app:cornerRadius="16dp"
    app:icon="@drawable/ic_icon"
    app:iconTint="@color/white"
    android:elevation="6dp" />
```

### Secondary Button (Outlined)
```xml
<com.google.android.material.button.MaterialButton
    style="@style/Widget.Material3.Button.OutlinedButton"
    android:layout_width="match_parent"
    android:layout_height="56dp"
    android:text="Button Text"
    android:textColor="#4CAF50"
    app:strokeColor="#4CAF50"
    app:cornerRadius="16dp" />
```

### Chip
```xml
<com.google.android.material.chip.Chip
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Chip Text"
    app:chipBackgroundColor="#E8F5E9"
    app:chipStrokeWidth="0dp"
    android:textColor="#2E7D32"
    android:textSize="12sp" />
```

### FloatingActionButton
```xml
<com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="FAB Text"
    android:textColor="@color/white"
    app:icon="@drawable/ic_icon"
    app:iconTint="@color/white"
    app:backgroundTint="#FF9800"
    android:elevation="12dp" />
```

---

## 🎭 Gradient Templates

### Green Gradient (Vertical)
```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <gradient
        android:type="linear"
        android:angle="270"
        android:startColor="#43A047"
        android:centerColor="#4CAF50"
        android:endColor="#66BB6A" />
</shape>
```

### Green Gradient (Diagonal)
```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <gradient
        android:type="linear"
        android:angle="135"
        android:startColor="#4CAF50"
        android:endColor="#81C784" />
</shape>
```

### Card with Gradient
```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <gradient
        android:type="linear"
        android:angle="45"
        android:startColor="#43A047"
        android:endColor="#66BB6A" />
    <corners android:radius="20dp" />
</shape>
```

---

## 🔤 Typography Scale

### Font Families
```xml
<!-- Use system fonts -->
<item name="android:fontFamily">sans-serif</item>
<item name="android:fontFamily">sans-serif-medium</item>
<item name="android:fontFamily">serif</item>

<!-- For quotes and special text -->
<TextView
    android:fontFamily="serif"
    android:textStyle="italic" />
```

### Text Styles
```xml
<!-- Display Large -->
<TextView
    android:textSize="36sp"
    android:textStyle="bold"
    android:textColor="#212121" />

<!-- Headline -->
<TextView
    android:textSize="24sp"
    android:textStyle="bold"
    android:textColor="#212121" />

<!-- Title -->
<TextView
    android:textSize="18sp"
    android:textStyle="bold"
    android:textColor="#212121" />

<!-- Body -->
<TextView
    android:textSize="14sp"
    android:textColor="#424242"
    android:lineSpacingExtra="4dp" />

<!-- Caption -->
<TextView
    android:textSize="12sp"
    android:textColor="#757575" />
```

---

## 🎨 Icon Guidelines

### Icon Sizes
```
Small: 16dp
Medium: 24dp
Large: 32dp
XLarge: 48dp
Hero: 64dp+
```

### Emoji as Icons
Use emoji for friendly, approachable feel:
```
🕌 Zakat/Masjid
💰 Money/Donation
🎁 Gift/Reward
📊 Statistics
👤 User/Profile
🔔 Notification
💚 Heart/Love
⚡ Quick/Fast
🌟 Star/Premium
🏆 Achievement
📍 Location
✨ Special/Magic
💎 Premium/Valuable
🔥 Streak/Hot
```

---

## 📱 Layout Patterns

### List Item Pattern
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="16dp"
    android:gravity="center_vertical">
    
    <!-- Icon/Avatar -->
    <ImageView
        android:layout_width="48dp"
        android:layout_height="48dp" />
    
    <!-- Content -->
    <LinearLayout
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:layout_marginStart="16dp">
        
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="16sp"
            android:textStyle="bold" />
        
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="14sp"
            android:textColor="#757575" />
    </LinearLayout>
    
    <!-- Action -->
    <ImageButton
        android:layout_width="40dp"
        android:layout_height="40dp" />
</LinearLayout>
```

### Card Grid Pattern (2 columns)
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">
    
    <MaterialCardView
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="120dp"
        android:layout_marginEnd="8dp" />
    
    <MaterialCardView
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="120dp" />
</LinearLayout>
```

---

## 🎬 Animation Guidelines

### Durations
```
Micro: 100ms (button press)
Short: 200ms (fade, scale)
Medium: 300ms (slide, expand)
Long: 500ms (complex)
```

### Common Animations
```xml
<!-- Fade In -->
<alpha
    android:duration="300"
    android:fromAlpha="0.0"
    android:toAlpha="1.0" />

<!-- Slide Up -->
<translate
    android:duration="300"
    android:fromYDelta="100%"
    android:toYDelta="0%" />

<!-- Scale -->
<scale
    android:duration="200"
    android:fromXScale="0.8"
    android:fromYScale="0.8"
    android:toXScale="1.0"
    android:toYScale="1.0"
    android:pivotX="50%"
    android:pivotY="50%" />
```

---

## ✅ Checklist: Membuat Screen Baru

- [ ] Gunakan CoordinatorLayout atau ConstraintLayout
- [ ] AppBar dengan gradient background
- [ ] Consistent spacing (16dp padding)
- [ ] MaterialCardView dengan elevation 4-8dp
- [ ] Corner radius minimal 12dp
- [ ] Text size sesuai hierarchy
- [ ] Primary button di bottom (56-64dp height)
- [ ] Loading state
- [ ] Empty state dengan ilustrasi
- [ ] Error state handling
- [ ] Accessibility labels
- [ ] Dark mode compatible

---

## 🚀 Quick Commands

### Generate Drawable
```bash
# Create new drawable
New File → drawable → gradient_my_name.xml
```

### Common Attributes
```xml
<!-- For all cards -->
app:cardCornerRadius="16dp"
app:cardElevation="4dp"

<!-- For all buttons -->
android:textAllCaps="false"
android:letterSpacing="0"

<!-- For all text -->
android:lineSpacingExtra="4dp"
```

---

## 💡 Pro Tips

1. **Use Vector Drawables** instead of PNGs
2. **Prefer ConstraintLayout** for complex layouts
3. **Use styles.xml** for reusable components
4. **Test on multiple screen sizes**
5. **Enable Layout Inspector** for debugging
6. **Use meaningful IDs** (btnSubmit, tvTitle)
7. **Add contentDescription** for accessibility
8. **Use dp for sizes**, sp for text
9. **Avoid hardcoded strings** (use strings.xml)
10. **Keep XML indented** properly

---

## 📚 Resources

### Official Docs
- [Material Design 3](https://m3.material.io/)
- [Android Layouts](https://developer.android.com/guide/topics/ui/declaring-layout)

### Design Tools
- [Figma](https://figma.com)
- [Material Theme Builder](https://material-foundation.github.io/material-theme-builder/)

### Color Tools
- [Coolors.co](https://coolors.co)
- [Material Color Tool](https://material.io/resources/color/)

### Icon Resources
- [Material Icons](https://fonts.google.com/icons)
- [Flaticon](https://flaticon.com)
- [Emoji Copy](https://emojicopy.com)

---

*Keep this guide handy when designing! 🎨*
