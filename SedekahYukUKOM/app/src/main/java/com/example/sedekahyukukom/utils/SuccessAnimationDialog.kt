package com.example.sedekahyukukom.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.example.sedekahyukukom.R
import com.google.android.material.button.MaterialButton

class SuccessAnimationDialog(
    private val context: Context,
    private val amount: String,
    private val message: String = "Semoga menjadi amal jariyah\ndan keberkahan bagi Anda",
    private val onViewHistory: (() -> Unit)? = null,
    private val onClose: (() -> Unit)? = null
) {

    fun show() {
        val dialog = Dialog(context, android.R.style.Theme_Material_Light_Dialog_NoActionBar)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_success_animation, null)
        dialog.setContentView(view)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // Get views
        val outerRing = view.findViewById<android.view.View>(R.id.outerRing)
        val middleRing = view.findViewById<android.view.View>(R.id.middleRing)
        val successIcon = view.findViewById<ImageView>(R.id.successIcon)
        val star1 = view.findViewById<ImageView>(R.id.star1)
        val star2 = view.findViewById<ImageView>(R.id.star2)
        val star3 = view.findViewById<ImageView>(R.id.star3)
        val star4 = view.findViewById<ImageView>(R.id.star4)
        val tvTitle = view.findViewById<TextView>(R.id.tvSuccessTitle)
        val tvAmount = view.findViewById<TextView>(R.id.tvSuccessAmount)
        val tvMessage = view.findViewById<TextView>(R.id.tvSuccessMessage)
        val layoutReward = view.findViewById<android.view.View>(R.id.layoutReward)
        val btnOk = view.findViewById<MaterialButton>(R.id.btnOk)
        val btnClose = view.findViewById<MaterialButton>(R.id.btnClose)

        // Set data
        tvAmount.text = amount
        tvMessage.text = message

        dialog.show()

        // Start animations sequence
        startSuccessAnimation(
            outerRing, middleRing, successIcon,
            star1, star2, star3, star4,
            tvTitle, tvAmount, tvMessage, layoutReward,
            btnOk, btnClose
        )

        // Button listeners
        btnOk.setOnClickListener {
            dialog.dismiss()
            onViewHistory?.invoke()
        }

        btnClose.setOnClickListener {
            dialog.dismiss()
            onClose?.invoke()
        }
    }

    private fun startSuccessAnimation(
        outerRing: android.view.View,
        middleRing: android.view.View,
        successIcon: ImageView,
        star1: ImageView, star2: ImageView, star3: ImageView, star4: ImageView,
        tvTitle: TextView, tvAmount: TextView, tvMessage: TextView,
        layoutReward: android.view.View,
        btnOk: MaterialButton, btnClose: MaterialButton
    ) {
        // 1. Outer Ring Expansion (0-300ms)
        val outerRingScale = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(outerRing, "scaleX", 0f, 1.2f).apply {
                    duration = 300
                    interpolator = OvershootInterpolator()
                },
                ObjectAnimator.ofFloat(outerRing, "scaleY", 0f, 1.2f).apply {
                    duration = 300
                    interpolator = OvershootInterpolator()
                },
                ObjectAnimator.ofFloat(outerRing, "alpha", 0f, 0.3f).apply {
                    duration = 300
                }
            )
        }

        // 2. Middle Ring Expansion (100-400ms)
        val middleRingScale = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(middleRing, "scaleX", 0f, 1.1f).apply {
                    duration = 300
                    interpolator = OvershootInterpolator()
                },
                ObjectAnimator.ofFloat(middleRing, "scaleY", 0f, 1.1f).apply {
                    duration = 300
                    interpolator = OvershootInterpolator()
                },
                ObjectAnimator.ofFloat(middleRing, "alpha", 0f, 0.5f).apply {
                    duration = 300
                }
            )
            startDelay = 100
        }

        // 3. Success Icon Pop (200-800ms)
        val successIconAnim = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(successIcon, "scaleX", 0f, 1f).apply {
                    duration = 600
                    interpolator = BounceInterpolator()
                },
                ObjectAnimator.ofFloat(successIcon, "scaleY", 0f, 1f).apply {
                    duration = 600
                    interpolator = BounceInterpolator()
                },
                ObjectAnimator.ofFloat(successIcon, "rotation", 0f, 360f).apply {
                    duration = 600
                    interpolator = AccelerateDecelerateInterpolator()
                }
            )
            startDelay = 200
        }

        // 4. Stars Burst Animation (600-1000ms)
        val starsAnim = AnimatorSet().apply {
            playTogether(
                // Star 1 - Top Left
                createStarAnimation(star1, -50f, -50f),
                // Star 2 - Top Right
                createStarAnimation(star2, 50f, -50f),
                // Star 3 - Bottom Left
                createStarAnimation(star3, -50f, 50f),
                // Star 4 - Bottom Right
                createStarAnimation(star4, 50f, 50f)
            )
            startDelay = 600
        }

        // 5. Pulse Ring Animation (continuous)
        val pulseAnim = ValueAnimator.ofFloat(1f, 1.15f, 1f).apply {
            duration = 1500
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { animator ->
                val scale = animator.animatedValue as Float
                outerRing.scaleX = scale
                outerRing.scaleY = scale
            }
            startDelay = 800
        }

        // 6. Title Fade In (800-1200ms)
        val titleAnim = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(tvTitle, "alpha", 0f, 1f).apply {
                    duration = 400
                },
                ObjectAnimator.ofFloat(tvTitle, "translationY", 30f, 0f).apply {
                    duration = 400
                    interpolator = OvershootInterpolator()
                }
            )
            startDelay = 800
        }

        // 7. Amount Scale In (1000-1500ms)
        val amountAnim = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(tvAmount, "alpha", 0f, 1f).apply {
                    duration = 500
                },
                ObjectAnimator.ofFloat(tvAmount, "scaleX", 0.5f, 1f).apply {
                    duration = 500
                    interpolator = BounceInterpolator()
                },
                ObjectAnimator.ofFloat(tvAmount, "scaleY", 0.5f, 1f).apply {
                    duration = 500
                    interpolator = BounceInterpolator()
                }
            )
            startDelay = 1000
        }

        // 8. Message Fade In (1300-1700ms)
        val messageAnim = ObjectAnimator.ofFloat(tvMessage, "alpha", 0f, 1f).apply {
            duration = 400
            startDelay = 1300
        }

        // 9. Reward Layout Slide In (1500-2000ms)
        val rewardAnim = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(layoutReward, "alpha", 0f, 1f).apply {
                    duration = 500
                },
                ObjectAnimator.ofFloat(layoutReward, "translationY", 50f, 0f).apply {
                    duration = 500
                    interpolator = OvershootInterpolator()
                }
            )
            startDelay = 1500
        }

        // 10. Buttons Fade In (1700-2100ms)
        val buttonsAnim = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(btnOk, "alpha", 0f, 1f).apply {
                    duration = 400
                },
                ObjectAnimator.ofFloat(btnOk, "translationY", 30f, 0f).apply {
                    duration = 400
                },
                ObjectAnimator.ofFloat(btnClose, "alpha", 0f, 1f).apply {
                    duration = 400
                },
                ObjectAnimator.ofFloat(btnClose, "translationY", 30f, 0f).apply {
                    duration = 400
                }
            )
            startDelay = 1700
        }

        // Start all animations
        outerRingScale.start()
        middleRingScale.start()
        successIconAnim.start()
        starsAnim.start()
        pulseAnim.start()
        titleAnim.start()
        amountAnim.start()
        messageAnim.start()
        rewardAnim.start()
        buttonsAnim.start()
    }

    private fun createStarAnimation(star: ImageView, translateX: Float, translateY: Float): AnimatorSet {
        return AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(star, "alpha", 0f, 1f, 0f).apply {
                    duration = 1000
                },
                ObjectAnimator.ofFloat(star, "translationX", 0f, translateX).apply {
                    duration = 1000
                    interpolator = AccelerateDecelerateInterpolator()
                },
                ObjectAnimator.ofFloat(star, "translationY", 0f, translateY).apply {
                    duration = 1000
                    interpolator = AccelerateDecelerateInterpolator()
                },
                ObjectAnimator.ofFloat(star, "rotation", 0f, 360f).apply {
                    duration = 1000
                },
                ObjectAnimator.ofFloat(star, "scaleX", 1f, 1.5f, 0.5f).apply {
                    duration = 1000
                },
                ObjectAnimator.ofFloat(star, "scaleY", 1f, 1.5f, 0.5f).apply {
                    duration = 1000
                }
            )
        }
    }
}
