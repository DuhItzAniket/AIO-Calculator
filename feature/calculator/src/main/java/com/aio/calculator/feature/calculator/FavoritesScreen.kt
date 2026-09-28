package com.aio.calculator.feature.calculator

import androidx.compose.foundation.clickable.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemIcon
import androidx.compose.material3.ListItemSecondaryAction
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.clickable.onClick
import androidx.compose.ui.unit.dp
import com.aio.calculator.core.common.ToolDefinition
import com.aio.calculator.core.common.ToolRegistry

/**
 * Favorites Screen - Shows favorite tools
 */
@Composable
fun FavoritesScreen(
    onBack: () -> Unit,
    favoriteToolIds: Set<String>,
    onToggleFavorite: (String) -> Unit,
    onSelectFavorite: (String) -> Unit
) {
    val calcColors = MaterialTheme.colorScheme
    val favoriteTools = ToolRegistry.getFavorites(favoriteToolIds)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
    ) {
        // Header
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = calcColors.surface
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Favorites",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = calcColors.onSurface
                )

                // Count of favorites
                Text(
                    text = "${favoriteTools.size} favorites",
                    fontSize = 12.sp,
                    color = calcColors.onSurface.copy(alpha = 0.5f)
                )
            }
        }

        // Favorites list
        if (favoriteTools.isEmpty()) {
            EmptyFavoritesState(onBack = onBack)
        } else {
            androidx.compose.foundation.layout
                .padding(Modifier.padding(16.dp))) {
                androidx.compose.foundation.layout
                    .padding(Modifier.padding(16.dp)))}
                        }}
                            }}
                                }}
                                    }}
                                        }}
                                            }}
                                                }}
                                                    }}
                                                        }}
                                                            }}
                                                                }}
                                                                    }}
                                                                        }}
                                                                          }}
                                                                        }}
                                                                      }}
                                                                    }}
                                                                  }}
                                                                }}
                                                            }}
                                                        }}
                                                    }}
                                                }}
                                              }}
                                            }}
                                        }}
                                      }}
                                    }}
                                  }}
                                }}
                            }}
                        }}
                    }}
                }}
              }}
            }}
          }}
        }}
      }}
    }}
}

/** Empty favorites state */
@Composable
fun EmptyFavoritesState(onBack: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Icon(
                imageVector = androidx.compose.material.Icons.Default.Favorite,
                contentDescription = "No favorites yet",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = "No favorites yet",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "Tap the star on any tool to add it here",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Browse Tools",
                    fontSize = 16.sp
                )
            }
        }
    }
}

/** Favorite list item */
@Composable
fun FavoriteListItem(
    tool: ToolDefinition,
    isFavorite: Boolean,
    onToggle: () -> Unit,
    onSelect: () -> Unit
) {
    val calcColors = MaterialTheme.colorScheme

    ListItem(
        modifier = Modifier.fillMaxWidth(),
        display = ListItemDisplay.PrimaryAndSecondary,
        leading = {
            // Star icon - filled if favorite, outline if not
            Icon(
                imageVector = if (isFavorite) androidx.compose.material.Icons.Filled.Favorite else androidx.compose.material.Icons.Outlined.Favorite,
                tint = if (isFavorite) calcColors.primary else calcColors.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                ListItemCaption(
                    primary = tool.title,
                    secondary = tool.category.displayName
                )
            }
        ),
        secondary = {
            // Favorite toggle button
            Button(
                onClick = onToggle,
                mini = true,
                colors = ButtonDefaults.buttonColors(
                    containerColor = calcColors.surface,
                    contentColor = calcColors.onSurface
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) androidx.compose.material.Icons.Filled.Favorite else androidx.compose.material.Icons.Outlined.Favorite,
                    tint = calcColors.primary,
                    contentDescription = "Toggle favorite"
                )
            }
        }
    )
}